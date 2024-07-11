const token = getQueryParameter('token');
function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}
function performPasswordChange() {

    $("#send-request-button").attr("disabled", true);

    var password = $("#password").val();
    var repeatedPassword = $("#repeated-password").val();

    if(arePasswordsValid(password, repeatedPassword)) {
        sendPasswordResetRequest(password);
    } else {
        $("#send-request-button").prop( "disabled", false );
    }
}

function sendPasswordResetRequest(password) {
   $.ajax({
        url: "/api/crs/user/reset-password",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(
            {
                passwordHash: hash(password),
                token: token,
            })
    })
        .done(function () {
            $("#error-alert").addClass('d-none');
            $("#notify-alert").removeClass('d-none');
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $("#send-request-button").attr("disabled", false);
            showError(prepareErrorMessage(jqxhr.responseText));
        });
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
