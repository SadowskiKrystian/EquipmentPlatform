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
function saveAttributeValue() {
    saveAttributes.forEach(function (saveAttribute) {
        attributeWithValueList.push(value = {id: saveAttribute.id, value: $("#attribute-id-" + saveAttribute.id + "").val(), attributeName: saveAttribute.name})
    });
    sendCreateRequest();
    attributeWithValueList = [];

}

function sendCreateRequest() {
    $.ajax({
        url: "/api/crs/equipment",
        method: "post",
        contentType: "application/json",
        data:
            JSON.stringify({
                name: $("#create-name").val(),
                values: attributeWithValueList
            })
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



