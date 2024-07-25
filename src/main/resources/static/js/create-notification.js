var usersId = [];
let choosenUserId = [];
$(document).ready(function () {
    findUsers();
});
function createNotification() {
    $.ajax({
        url: "/admin/api/crs/notification",
        type: "post",
        contentType: "application/json",
        data:
            JSON.stringify({
                users: choosenUserId,
                title: $("#title").val(),
                content: $("#content").val()
            })
    })
        .done(function () {
            $('#operation-successful-modal').modal('show');
            clearCreateNotifiactionModal();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}

function findUsers() {
    $.ajax({
        url: "/admin/api/crs/users",
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (users) {
            users.forEach(function (user) {
                fillCreateResults(user);
            })

        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}

function fillCreateResults(user) {
    $('#users').append(
        "<div>" +
        "<label for='user-" + user.id + "'>" + user.login + "</label>" +
        "<input type='checkbox' id='user-" + user.id + "' name='user '" + user.id + " value='" + user.login + "'/>" +
        "</div>"
    );
    usersId.push({id: user.id, login: user.login});
}

function saveChoosenReceiver() {
    let userLogin = "";
    for (let i = 0; i < usersId.length; i++) {
        if ($('#user-' + usersId[i].id).is(":checked")) {
            choosenUserId.push(usersId[i].id);
            userLogin += usersId[i].login + " ";
        }
    }
    $('#choosen-users').val(userLogin);
    $('#create-modal').modal('hide');
    return choosenUserId;
}

function cancel() {
    window.location.href = '/admin/notification/send'
}

function clearCreateNotifiactionModal() {
    $("#choosen-users").val('');
    $('#title').val('');
    $('#content').val('');
}



