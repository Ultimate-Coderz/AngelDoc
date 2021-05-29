<%@page import="Hibernate.Coordinator"%>
<%@page import="dao.CoordinatorDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Update Details</title>
	
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
		rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
	<!-- Favicons -->
	<link href="assets/img/favicon.png" rel="icon">
	<link href="assets/img/favicon.png" rel="apple-touch-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet"
	 integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
		crossorigin="anonymous">
	
</head>

<body>

	<%
		if(session.getAttribute("userObj")==null)
		{
			response.sendRedirect("DashboardServ");
		}	
	%>

	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div class="container d-flex align-items-center">

			<a href="index.html" class="logo me-auto"><img
				src="assets/img/logo.JPG" alt="" class="img-fluid"></a>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<li><a class="nav-link scrollto active" href="index.jsp#hero">Home</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#about">About</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#services">Services</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#departments">Departments</a></li>
          <li><a class="nav-link scrollto" href="Search.jsp">Doctors</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#contact">Contact</a></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->
			
			<%
				Coordinator c=(Coordinator)session.getAttribute("userObj");
			%>

			<a href="Login.jsp" class="log-btn scrollto" style="text-decoration:none;"><span
				class="d-none d-md-inline">Hi, <%=c.getFirstName() %></span></a>

		</div>
	</header>
	<br><br>
<br>
	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-6 py-5 section-title">
				<h2 class="text-center ">Update Details</h2>


				<form id="coordinator" class="formSelect text-center " method="post"
					action="UpdateCoordinatorDash">
					<div class="row g-2">
						<div class="col-md">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" id="floatingInput" required
									placeholder="First" value="<%=c.getFirstName() %>" name="firstName" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" > <label
									for="floatingInput">First Name</label>
							</div>
						</div>
						<div class="col-md">
							<div class="form-floating mb-3">
								<input type="text" class="form-control" required value="<%=c.getLastName() %>" id="floatingInput" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name"
									placeholder="Last" name="lastName"> <label
									for="floatingInput">Last Name</label>
							</div>
						</div>
					</div>
					<div class="form-floating mb-3">
						<input type="email" class="form-control" id="floatingInput" required
							placeholder="Email" value="<%=c.getEMail() %>" name="email"> <label
							for="floatingInput">Email</label>
					</div>


					<div class="col-md">
						<div class="form-floating mb-3">
							<input type="number" class="form-control" id="floatingInput" required
								placeholder="Contact No." value="<%=c.getContactNumber() %>" name="contactNum"> <label
								for="floatingInput">Contact</label>
						</div>
					</div>
					<br> 
					<input type="submit" value="Submit" class="btn btn-primary">
				</form>


			</div>
		</div>
	</div>
</body>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-lpyLfhYuitXl2zRZ5Bn2fqnhNAKOAaM/0Kr9laMspuaMiZfGmfwRNFh8HlMy49eQ"
	crossorigin="anonymous"></script>

</html>