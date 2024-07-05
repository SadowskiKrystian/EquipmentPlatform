$(document).ready(function() {

});
const token = getQueryParameter('token');
function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

function sendVerification(){
    $.ajax({
        url: "/api/crs/verify",
        method: "POST",
        contentType: "application/json",
        data:
            JSON.stringify({
                sendToken: token
            })
    })
        .done(function() {
            alert("Verification successful");
            window.location.href = "/login";
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}


