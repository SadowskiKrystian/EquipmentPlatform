$(document).ready(function () {
    findEquipments();
});


function findEquipments() {
    $.ajax({
        url: "/api/crs/notifications",
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (response) {
            fillResults(response);
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}

function fillResults(response) {
    $("#notifications").empty();
    var notifications = response;
    notifications.forEach(function (notification) {
        fillRow(notification);
    });
}

function fillRow(notification) {
    $('#notifications').append(
        "<div  class='notification-div'>" +
        "<div><b>" + notification.title + "</b></div>" +
        "<div>" + notification.content + "</div>" +
        "<div class='text-muted small text-right'>" + notification.createDate + "</div>" +
        "<div class='notification-delete-button'><button class='btn btn-primary' onclick=''>" + "Delete" + "</button></div>" +
        "</div>"
    );
}
function cancel(){
    $("#create-name").val('');
    $('[id^="attribute-id-"]').val('');
}
function clearCreateModal() {
    $("#create-name").val('');
    $('[id^="attribute-id-"]').val('');
}



