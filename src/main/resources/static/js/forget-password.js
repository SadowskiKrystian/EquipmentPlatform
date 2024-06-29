
function sendForgetPasswordRequest() {
   $("#send-request-button").attr("disabled", true);
   $.ajax({
        url: "/api/crs/customer/forget-password/?email=" + $("#email").val(),
        method: "POST",
        contentType: "application/json",
    })
        .done(function () {
            $("#notify-alert").removeClass('d-none');
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $("#send-request-button").attr("disabled", false);
            displayErrorInformation(jqxhr.responseText);
        });
}