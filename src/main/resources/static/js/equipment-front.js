var saveValue = {};
var values = [];
var saveValues = [];
var assignedAttributes = new Map;
$(document).ready(function () {
    getEquipment();
    $("#operation-successful-modal").on('hidden.bs.modal', function () {
        window.location.href = '/equipments-front'
    })
});
function sendDelete(){
    $("#delete-object-modal").modal('hide');
}
function getEquipment() {
    $.ajax({
        url: "/api/crs/equipment/" + equipmentId,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (equipment) {
            prepareValues(equipment);
            fillEquipmentRow(equipment);
            fillAttribute(equipment);
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}
function sendDeleteRequest(){
    $.ajax({
        url: "/api/crs/equipment/" + equipmentId,
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
function saveUpdate(){
    assignedAttributes.forEach(function (value, id) {
        saveValues.push(saveValue = {id: id, value: $("#attribute-id-" + id + "").val(), assignedAttributeId: value.assignedAttributeId, attributeName: value.name});
    })
    sendUpdateRequest();
    saveValues = [];
}
function sendUpdateRequest() {
    $.ajax({
        url: "/api/crs/equipment/" + equipmentId,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            name: $("#name").val(),
            values: saveValues
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
function prepareValues(equipment){
    equipment.attributes.forEach(function (attribute){
        assignedAttributes.set(id = attribute.id, value = {value : "", name : attribute.name, type : attribute.type});
    });
    equipment.assignedAttributes.forEach(function (attribute) {
        assignedAttributes.set(id = attribute.id, value = {assignedAttributeId : attribute.assignedAttributeId, value : attribute.value, name : attribute.name, type : attribute.type});
    });
    return assignedAttributes;
}
function fillEquipmentRow(equipment){
    $('#equipment-row').append(
        "<div className='form-group col-md-6 text-center'>" +
        "<label class='form-check-label'>Name:</label>" +
        "<input type='text' class='form-control' id='name'" +
        "value='" + equipment.equipment.name + "'/>" +
        "</div>" +
        "<div className='form-check-label'>" +
            "<label for='create-date'>Create Date:</label>" +
            "<input type='datetime-local' id='create-date' class='form-control' disabled='true' " +
            "value='" + equipment.equipment.createDate + "'/>" +
        "</div>" +
        "<div className='form-group col-md-6 text-center'>" +
            "<label for='edit-date'>Edit Date:</label>" +
            "<input type='datetime-local' id='edit-date' class='form-control' disabled='true'" +
            "value='" + equipment.equipment.editDate + "'/>" +
        "</div>"
    )
}
function fillAttribute() {
    assignedAttributes.forEach(function (value, id) {
        fillRow(value, id);
    })
}
function fillRow(value, id){
    $('#value-records').append(
        "<label class='form-check-label'>" + value.name + "</label>" + (value.type !== 'DateTime'?
        "<input type='text' class='form-control' id='attribute-id-" + id + "' value='" + value.value + "'>" :
            "<input type='date' class='form-control' id='attribute-id-" + id + "' value='" + value.value + "'>")
    );
}



function showError(text) {
     $("#error-alert-text").text(text);
     $("#error-alert").removeClass('d-none');
}
function cancel(){
    window.location.href = '/equipments-front';
}
