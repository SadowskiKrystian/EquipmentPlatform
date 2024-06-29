var attributeType;
var attributeValues;
var types;
$(document).ready(function () {
    getAttribute();
    getAttributeTypesDictionary();
    $('#operation-successful-modal').on('hide.bs.modal', function (e) {
        window.location.href = '/admin/attributes';
    });
});
function getAttribute() {
    $.ajax({
        url: "/admin/api/crs/attribute/" + attributeId,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (data) {
          fillAttribute(data);
          attributeValues = data.assignedAttributes;
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}
function getAttributeTypesDictionary(){
    $.ajax({
        url: "/admin/api/crs/dictionaries",
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (data) {
          types = data;
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}
function sendDelete(){
    if (attributeValues.length === 0){
        $('#delete-object-modal').modal('show');
    }else{
        $('#delete-object-modal-with-value').modal('show');
    }
}
function sendDeleteRequest() {
    $.ajax({
        url: "/admin/api/crs/attribute/" + attributeId,
        type: "DELETE"
    })
        .done(function (response) {
            $('#delete-object-modal-with-value').modal('hide');
            $('#delete-object-modal').modal('hide');
            $('#operation-successful-modal').modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}
function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/crs/attribute/" + attributeId,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            name: $("#attribute-name").val(),
            type: $("#type-records option:selected").val(),
            domain: $("#domain option:selected").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
            $("#save-changes-button").prop( "disabled", false );
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}
function fillAttribute(attribute) {
    fillRow(attribute);
    attributeType = attribute.attribute.type;
    types.forEach(function (type){
        fillDictionary(type);
    });
}
function fillRow(attribute){
    $('#value-records').append(
        "<label class='form-check-label'>Name:</label>" +
        "<input type='text' class='form-control' id='attribute-name' value='" + attribute.attribute.name + "'>" +
        "<label class='form-check-label'>Type:</label>" +
        "<select id='type-records' class='form-control'>" +
        "</select>" +
        "<label class='form-check-label'>Domain:</label>" +
        "<select id='domain' class='form-control'> " +
        "<option>Equipment</option>" +
        "</select>" +
        "<label class='form-check-label'>Create Date:</label>" +
        "<input type='datetime-local' class='form-control' disabled='true' id='attribute-type' value='" + attribute.attribute.createDate + "'>" +
        "<label class='form-check-label'>Edit Date:</label>" +
        "<input type='datetime-local' class='form-control' disabled='true' id='attribute-type' value='" + attribute.attribute.editDate + "'>"
    );
}
function fillDictionary(type){
    $('#type-records').append(type.code === attributeType?
        "<option  value='" + type.value + "' selected>" + type.value + "</option>":
        "<option  value='" + type.value + "'>" + type.value + "</option>"

    );
}
function showError(text) {
     $("#error-alert-text").text(text);
     $("#error-alert").removeClass('d-none');
}
function cancel(){
    window.location.href = '/admin/attributes';
}
