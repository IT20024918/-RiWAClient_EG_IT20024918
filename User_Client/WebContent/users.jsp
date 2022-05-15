<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/users.js"></script>
<script src="Components/auth.js"></script>
</head>
<body>
<div> <input id="btnLogout" name="btnLogout" type="button" value="Logout"
 class="btn btn-primary"></div>
<div class="container"><div class="row"><div class="col-6">
<h1>User Management</h1>

 
 <form id="formItem" name="formItem">
 Account No:
 <input id="accountNo" name="accountNo" type="text"
 class="form-control form-control-sm">
 <br> Name:
 <input id="Name" name="Name" type="text"
 class="form-control form-control-sm">
 <br> Address:
 <input id="Address" name="Address" type="text"
 class="form-control form-control-sm">
 <br> NIC:
 <input id="NIC" name="NIC" type="text"
 class="form-control form-control-sm">
 <br>Email:
 <input id="Email" name="Email" type="text"
 class="form-control form-control-sm">
 <br>Phone:
 <input id="Phone" name="Phone" type="text"
 class="form-control form-control-sm">
 <br>Username:
 <input id="Username" name="Username" type="text"
 class="form-control form-control-sm">
 <br>Password:
 <input id="Password" name="Password" type="text"
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
 </form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 User userObj = new User();
 out.print(userObj.readUserDetails());
 %>
 
</div>
</div> </div> </div>
<br><br>


</body>
</html>