$(document).ready(function () {
    getAuthority();
    $('#operation-successful-modal').on('hide.bs.modal', function (e) {
        window.location.href = "/admin/users";
    });
});

function getUsersSite(){
    window.location.href = "/admin/users";
}
function getAuthority(){
    $.ajax({
        url: "/admin/api/crs/authority/" + user.id,
        type: "GET"
    })
        .done(function(response) {
            fillRoles(response);
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}
function fillRoles(authorities) {
    authorities.forEach(function(authority){
        $('#'+authority.authority).attr('checked', true);
    })
}
function sendDeleteCustomer(){
    $('#delete-object-modal').modal('hide');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/crs/user/" + user.id,
        type: "DELETE"
    })
        .done(function(response) {
            $('#delete-object-modal').modal('hide');
            $('#operation-successful-modal').modal('show');
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

 function performPasswordChange() {

        $("#change-password-button").prop( "disabled", true );

        var password = $("#new-password").val();
        var repeatedPassword = $("#repeated-new-password").val();

        if(arePasswordsValid(password, repeatedPassword)) {
            sendPasswordChangeRequest(password);
        } else {
            $("#change-password-button").prop( "disabled", false );
        }
}
function sendPasswordChangeRequest(password) {
       $.ajax({
               url: "/admin/api/crs/user-change-password/"  + user.id,
               method: "put",
               contentType: "application/json",
               data: JSON.stringify(
                   {
                       newPasswordHash: hash(password),
                   })
           })
               .done(function () {
                   $("#operation-successful-modal").modal('show');
               })
               .fail(function(jqxhr, textStatus, errorThrown){
                   $("#change-password-button").prop("disabled", false);
                   showError(prepareErrorMessage(jqxhr.responseText));
               });
}
function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/crs/user/" + user.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            login: $("#login").val(),
            language: $("#language").find(":selected").val(),
            emailConfirmed: $("#email-confirmed").find("option:selected").val(),
            authorities : getAuthorities()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
            $("#save-changes-button").prop( "disabled", false );
            alert();
        })
}
function getAuthorities(){
         var authorities = [];
         for(i = 0; i < authorityDict.length; i++) {
             if($('#'+authorityDict[i].code).is(":checked")){
                 authorities.push(authorityDict[i].code);
             }
         }
          return authorities;
 }
 function showError(text) {
     $("#error-alert-text").text(text);
     $("#error-alert").removeClass('d-none');
}
