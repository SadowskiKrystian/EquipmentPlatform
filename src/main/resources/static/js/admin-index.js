
function killApp() {
    $.ajax({
        url: "/actuator/shutdown",
        type: "post",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function () {
        alert("Application killed!");
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}