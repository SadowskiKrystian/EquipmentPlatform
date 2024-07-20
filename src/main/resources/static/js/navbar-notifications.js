
let notificationsBadge;
let notificationsCounter;
let notificationsDropdown;
let notificationsDropdownBody;
let id;

$(document).ready(function () {
    notificationsBadge = $("#notifications-badge");
    notificationsCounter = $("#notifications-counter");
    notificationsDropdown = $("#notifications-dropdown");
    notificationsDropdownBody = $("#notifications-dropdown-body");

    notificationsDropdown.on('shown.bs.dropdown', function (){
        findNotifications();
    });

    notificationsDropdown.on('hidden.bs.dropdown', function (){
        setDropdownLoading();
    })

    if(notificationsBadge.attr("notifications-active") === 'true') {
        findNotificationsCount();
    }

});

function findNotificationsCount() {
    $.ajax({
        url: "/api/crs/notifications/count/unseen",
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (response) {
            fillNotificationsCounter(response);
            setTimeout(() => {findNotificationsCount();}, 5000);
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

function fillNotificationsCounter(notificationsCount) {
    if(notificationsCount > 0) {
        notificationsCounter.html(notificationsCount);
        notificationsBadge.show();
    } else {
        notificationsBadge.hide();
        notificationsCounter.html('');
    }
}

function findNotifications() {
    $.ajax({
        url: "/api/crs/notifications",
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (response) {
            fillDropdownBody(response);
           // fillNotificationsCounter(0);
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

function setDropdownLoading() {
    let code = '';
    code += '<div class="text-center">';
    code +=     '<div class="spinner-border" role="status">';
    code +=         '<span class="sr-only">Loading...</span>';
    code +=     '</div>';
    code += '</div>';

    notificationsDropdownBody.empty();
    notificationsDropdownBody.append(code);

}

function fillDropdownBody(notifications) {

    notificationsDropdownBody.empty();

    if(notifications.length > 0) {
        notifications.forEach(function (notification) {
            notificationsDropdownBody.append(prepareNotificationCode(notification));
        });
    } else {
        notificationsDropdownBody.append('<div class="text-center p-5">' + lang.noNotifications + '</div>')
    }

}

function prepareNotificationCode(notification) {
    id = notification.id;
    let link = notification.link;
    let createDatetime = notification.createDate;
    let title = notification.title;
    let content = notification.content;
    let newInfo = '';

    if(notification.seen) {
        newInfo = '<span style="color: red"><i class="fas fa-circle"></i> </span>';
    }

    let code = '';
    code += '<a class="dropdown-item" href="/notifications" onclick="updateSeenNotifications()">';
    code +=     '<div><b>' + newInfo + title + '</b></div>';
    code +=     '<div>' + content + '</div>';
    code +=     '<div class="text-muted small text-right">' + createDatetime + '</div>';
    code += '</a>';
    code += '<div class="dropdown-divider"></div>';

    return code;
}

function updateSeenNotifications() {
    $.ajax({
        url: "/api/crs/notification/" + id,
        type: "PUT",
        dataType: "json",
        contentType: "application/json"
    })
        .done( {

        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

