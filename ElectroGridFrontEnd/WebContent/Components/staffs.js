$(document).ready(function()
{ 
 $("#alertSuccess").hide();
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateStaffForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidStaffIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
 url : "StaffsAPI",
 type : type,
 data : $("#formStaff").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onStaffSaveComplete(response.responseText, status);
 }
});
});
function onStaffSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divStaffsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidStaffIDSave").val("");
 $("#formStaff")[0].reset();
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidStaffIDSave").val($(this).closest("tr").find('#hidStaffIDUpdate').val());
 $("#CustomerName").val($(this).closest("tr").find('td:eq(0)').text());
 $("#CustomerID").val($(this).closest("tr").find('td:eq(1)').text());
 $("#StaffName").val($(this).closest("tr").find('td:eq(2)').text());
 $("#AssignWork").val($(this).closest("tr").find('td:eq(3)').text());
 $("#Mobile").val($(this).closest("tr").find('td:eq(4)').text());

});

//delete
$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "StaffsAPI",
 type : "DELETE",
 data : "staffID=" + $(this).data("staffid"),
 dataType : "text",
 complete : function(response, status)
 {
 onStaffDeleteComplete(response.responseText, status);
 }
 });
});
function onStaffDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divStaffsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#CustomerName").val().trim() == "")
 {
 return "Insert Customer Name.";
 }
// NAME
if ($("#CustomerID").val().trim() == "")
 {
 return "Insert Customer ID.";
 } 
 // PRICE-------------------------------
if ($("#StaffName").val().trim() == "")
 {
 return "Insert Staff Name.";
 }

// DESCRIPTION------------------------
if ($("#AssignWork").val().trim() == "")
 {
 return "Insert AssignWork.";
 }
 // DESCRIPTION------------------------
if ($("#Mobile").val().trim() == "")
 {
 return "Insert Mobile.";
 }
return true;
}
