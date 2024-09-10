var value = {};
var saveAttributes;
var attributeWithValueList = [];

$(document).ready(function () {
    $("#operation-successful-modal").on('hidden.bs.modal', function () {
        window.location.href = '/equipments-front'
    })
    findAttributes();
});


function findAttributes() {
    $.ajax({
        url: "/api/crs/equipments/attributes",
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
function fillCreateResults(attributes){
    $('#create-record').append(
        "<form class='text-center'>" +
        "<div class='form-group'>" +
        "<label class='form-check-label'>Name:</label>" +
        "<input type='text' class='form-control' id='create-name'/>" +
        "</div>" +
        "<h6>Attributes:</h6>" +
        "<div id='attributes-record'>" +
        "       </div>" +
        "</form>" +
        "<form id='uploadForm' class='text-center'>" +
        "<div class='form-group'>" +
        "<label class='form-check-label'>Picture:</label>" +
        "</div>" +
        "<div class='form-group'>" +
        "<input type='file' id='fileInput' name='file' class='form-check-label'>" +
        // "<button type='submit'>Upload</button>" +
        "</div>" +
        "</form>"
    );
    saveAttributes = attributes;
    attributes.forEach(function (attribute){
        fillCreateRow(attribute);
    })
}
function fillCreateRow(attribute) {
    $('#attributes-record').append(
        "<div class='form-group'>" +
        "<label class='form-check-label'>" + attribute.name + "</label>" + (attribute.type !== 'DateTime'?
            "<input type='text' class='form-control' id='attribute-id-" + attribute.id + "'>" :
            "<input type='date' class='form-control' id='attribute-id-" + attribute.id + "'>") +
        "</div>"
    );
}
function saveAttributeValue() {
    saveAttributes.forEach(function (saveAttribute) {
        attributeWithValueList.push(value = {id: saveAttribute.id, value: $("#attribute-id-" + saveAttribute.id + "").val(), attributeName: saveAttribute.name})
    });
    sendCreateRequest();
    attributeWithValueList = [];

}

function sendCreateRequest() {
    var formData = new FormData;
    var image = $('input[name="file"]').get(0).files[0];
    if (image !== null){
        formData.append('file', image);
    }
    formData.append('json', JSON.stringify({
        name : $("#create-name").val(),
        values: attributeWithValueList
    }))
    $.ajax({
        url: "/api/crs/equipment",
        type:"POST",
        processData:false,
        contentType: false,
        data: formData,
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
            clearCreateModal();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            $("#create-button").prop("disabled", false);
            displayErrorInformation(jqxhr.responseText);
        })

}

function cancel(){
    window.location.href = '/equipments-front'
    // $("#create-name").val('');
    // $('[id^="attribute-id-"]').val('');
}
function clearCreateModal() {
    $("#create-name").val('');
    $('[id^="attribute-id-"]').val('');
}



