<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>SalesRep Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<script type="text/javascript">
	function() {
		'use strict';
		window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
		form.addEventListener('submit', function(event) {
		if (form.checkValidity() === false) 
		  {
		event.preventDefault();
		event.stopPropagation();
		  }
		form.classList.add('was-validated');
		  }, false);
		  });
		  }, false);
		}
	
	</script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> SalesRep Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form class="needs-validation" action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form class="needs-validation" action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group" >
					<label for="validationCustom01" >Full Name</label> 
					<input type="text"
						value="<c:out value='${user.name}' />" class="form-control" id="validationCustom01"
						name="name" required="required">
						<div class="invalid-feedback">
                            Please provide your name.
                        </div>
				</fieldset>

				<fieldset class="form-group">
					<label for="validationCustom02" >Email Address</label> 
					<input type="text"
						value="<c:out value='${user.email}' />" class="form-control" id="validationCustom02"
						name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required">
						<div class="invalid-feedback">
                            Please provide a valid email.
                        </div>
				</fieldset>

				<fieldset class="form-group">
					<labelfor="validationCustom03" >Telephone</label> <input type="text"
						value="<c:out value='${user.contactNumber}' />" class="form-control" id="validationCustom03"
						name="contactNumber">
						<div class="invalid-feedback">
                            Please provide a valid contact number.
                        </div>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Joined Date</label> <input type="text"
						value="<c:out value='${user.joinedDate}' />" class="form-control"
						name="joinedDate">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Current Routes</label> <input type="text"
						value="<c:out value='${user.cuurentRoute}' />" class="form-control"
						name="cuurentRoute">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Comments</label> <input type="text"
						value="<c:out value='${user.comment}' />" class="form-control"
						name="comment">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>