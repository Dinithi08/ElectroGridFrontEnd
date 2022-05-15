<%@page import="com.Staff"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Work Assign Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/staffs.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Staff Work Assign Details</h1>
<form id="formStaff" name="formStaff">
 Customer Name:
 <input id="customerName" name="customerName" type="text"
 class="form-control form-control-sm">
 <br> Customer ID:
 <input id="customerID" name="customerID" type="text"
 class="form-control form-control-sm">
 <br> Staff Name:
 <input id="staffName" name="staffName" type="text"
 class="form-control form-control-sm">
 
 <br>Assign Work:
 <input id="assignWork" name="assignWork" type="text"
 class="form-control form-control-sm">
 <br>Mobile:
 <input id="mobile" name="mobile" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidStaffIDSave"
 name="hidStaffIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divStaffsGrid">
 <%
 Staff staffObj = new Staff();
 out.print(staffObj.readStaffs());
 %>
</div>
</div> </div> </div>
</body>
</html>