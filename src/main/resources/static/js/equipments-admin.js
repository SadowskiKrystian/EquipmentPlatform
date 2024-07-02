var objToDeleteId;
var value = {};
var saveAttributes;
var attributeWithValueList = [];

$(document).ready(function () {
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findEquipments();
    });
    findAttributes();
    findEquipments();
});


function findEquipments() {
    $.ajax({
        url: "/admin/api/crs/equipments",
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

function findAttributes() {
    $.ajax({
        url: "/admin/api/crs/equipments/attributes",
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
        .done(function (response) {
            fillCreateResults(response)
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}
function fillResults(response) {
    $("#records").empty();
    var equipments = response;
    equipments.forEach(function (equipment) {
        //fillRow(equipment);
    });
}

function fillRow(equipment) {
    $('#records').append(
        "<tr>" +
        "<td class='align-middle'>" + equipment.id + "</td>" +
        "<td class='align-middle'>" + equipment.equipmentUserGetResponse.login + "</td>" +
        "<td class='align-middle'>" + equipment.name + "</td>" +
        "<td class='align-middle'>" + equipment.createDate + "</td>" +
        "<td class='align-middle'>" + equipment.editDate + "</td>" +
        "<td class='align-middle'>" + prepareDetailsButton(equipment.id) + "</td>" +
        "<td class='align-middle'>" + prepareDeleteButton(equipment.id) + "</td>" +
        "</tr>"
    );
}
function fillCreateResults(attributes){
    $('#create-record').append(
        "<form className='text-center'>" +
        "<div className='form-group'>" +
        "<label class='form-check-label'>Name:</label>" +
        "<input type='text' class='form-control' id='create-name'/>" +
        "</div>" +
        "<h6>Attributes:</h6>" +
        "<div id='attributes-record'>" +
        "       </div>" +
        "</form>"
    );
    saveAttributes = attributes;
    attributes.forEach(function (attribute){
        fillCreateRow(attribute);
    })
}
function fillCreateRow(attribute) {
    $('#attributes-record').append(
        "<div className='form-group'>" +
        "<label class='form-check-label'>" + attribute.name + "</label>" + (attribute.type !== 'DateTime'?
            "<input type='text' class='form-control' id='attribute-id-" + attribute.id + "'>" :
            "<input type='date' class='form-control' id='attribute-id-" + attribute.id + "'>") +
        "</div>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">' + lang.Details + '</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/equipment/" + id;
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ') ">' + lang.Delete + '</button>';
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#create-modal').on('hide.bs.modal', function (e) {
        sendDeleteRequest();
        clearCreateModal();
    });
    $('#delete-object-modal').modal('show');
}


function sendDeleteRequest() {
    console.log(objToDeleteId);
    $.ajax({
        url: "/admin/api/crs/equipment/" + objToDeleteId,
        type: "DELETE"
    })
        .done(function (response) {
            $('#delete-object-modal').modal('hide');
            $('#operation-successful-modal').modal('show');
            findEquipments();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        });
}

function saveAttributeValue() {
    saveAttributes.forEach(function (saveAttribute) {
        attributeWithValueList.push(value = {id: saveAttribute.id, value: $("#attribute-id-" + saveAttribute.id + "").val(), attributeName: saveAttribute.name})
    });
    sendCreateRequest();
    attributeWithValueList = [];

}

function sendCreateRequest() {
    $.ajax({
        url: "/admin/api/crs/equipment",
        method: "post",
        contentType: "application/json",
        data:
            JSON.stringify({
                name: $("#create-name").val(),
                values: attributeWithValueList
            })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findEquipments();
            clearCreateModal();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            $("#create-button").prop("disabled", false);
            displayErrorInformation(jqxhr.responseText);
        })

}
function cancel(){
    $("#create-name").val('');
    $('[id^="attribute-id-"]').val('');
}
function clearCreateModal() {
    $("#create-name").val('');
    $('[id^="attribute-id-"]').val('');
}



