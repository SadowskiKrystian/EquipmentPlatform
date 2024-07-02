$(document).ready(function () {
    findAttributes();
    getAttributeTypesDictionary();
});
function findAttributes() {
    $.ajax({
        url: "/admin/api/crs/attributes",
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (data) {
            fillResults(data)
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}
function findAttributeValues(id){
    $.ajax({
        url: "/admin/api/crs/attribute/value/" + id,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (data) {
           attributeWithValueSize = data.length;
           setObjectToDeleteIdAndShowModal(id);
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}
function fillResults(attributes) {
    $("#records").empty();
    attributes.forEach(function (attribute) {
        fillRow(attribute);
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
            fillCreate(data);
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

function fillRow(attribute) {
    $('#records').append(
        "<tr>" +
        "<td class='align-middle'>" + attribute.id + "</td>" +
        "<td class='align-middle'>" + attribute.name + "</td>" +
        "<td class='align-middle'>" + attribute.type + "</td>" +
        "<td class='align-middle'>" + attribute.domain + "</td>" +
        "<td class='align-middle'>" + attribute.createDate + "</td>" +
        "<td class='align-middle'>" + attribute.editDate + "</td>" +
        "<td class='align-middle'>" + prepareDetailsButton(attribute.id) + "</td>" +
        "<td class='align-middle'>" + prepareDeleteButton(attribute.id) + "</td>" +
        "</tr>"
    );
}
function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">' + lang.Details + '</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/attribute/" + id;
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="findAttributeValues(' + id + ') ">' + lang.Delete + '</button>';
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });
    if (attributeWithValueSize === 0) {
        $('#delete-object-modal').modal('show');
    }else{
        $('#delete-object-modal-with-value').modal('show');
    }
}
function sendDeleteRequest() {
    console.log(objToDeleteId);
    $.ajax({
        url: "/admin/api/crs/attribute/" + objToDeleteId,
        type: "DELETE"
    })
        .done(function () {
            $('#delete-object-modal').modal('hide');
            $('#delete-object-modal-with-value').modal('hide');
            $('#operation-successful-modal').modal('show');
            findAttributes();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}
function sendCreateRequest() {
    $.ajax({
        url: "/admin/api/crs/attribute",
        method: "post",
        contentType: "application/json",
        data:
            JSON.stringify({
                name: $("#attribute-name").val(),
                type: $("#type-records option:selected").val(),
                domain: $("#domain option:selected").val()
            })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findAttributes();
            clearCreateModal();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            $("#create-button").prop("disabled", false);
            displayErrorInformation(jqxhr.responseText);
        })

}
function clearCreateModal() {
    $("#attribute-name").val('');
    $("#type-records option").prop("selected", false);
}
function fillCreate(dictionaries){
    $('#create-records').append(
        "<label class='form-check-label'>Name:</label>" +
        "<input type='text' class='form-control' id='attribute-name'>" +
        "<label class='form-check-label'>Type:</label>" +
        "<select id='type-records' class='form-control'>" +
        "</select>" +
        "<label class='form-check-label'>Domain:</label>" +
        "<select id='domain' class='form-control'> " +
        "<option>Equipment</option>" +
        "</select>"
    );
    dictionaries.forEach(function (dictionary){
        fillCreateDictionary(dictionary);
    })
}
function fillCreateDictionary(dictionary){
    $('#type-records').append(
        "<option value='" + dictionary.value + "'>" + dictionary.value + "</option>"
    );
}



