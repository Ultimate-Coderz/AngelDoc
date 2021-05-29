<%@page import="java.io.File"%>
<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
			rel="stylesheet">
		<link href="assets/css/style.css" rel="stylesheet">
		<!-- Favicons -->
		<link href="assets/img/favicon.png" rel="icon">
		<link href="assets/img/favicon.png" rel="apple-touch-icon">
		<title>Download Prescription</title>
		<style>
		.checks {
			background-color: rgb(52, 193, 240);
			border: 2px solid #FFF;
			padding: 10px;
			color: white;
			border-radius: 10px;
			text-decoration: none;
		}
		
		.checks:hover {
			background-color: cadetblue;
			color: cornsilk;
			transform: translateY(-20px);
		}
		</style>
	</head>
	<body>
		<%
		
			if (request.getSession(false) == null) {
				System.out.println("Session is null!!!");
				response.sendRedirect("Login.jsp");
			}
		%>
	
		<%
		if (session.getAttribute("userObj") == null) {
			response.sendRedirect("Login.jsp");
		}
		
			String appId=(String)request.getParameter("appId");
			System.out.println("------------------------------------------"+appId);
			String name = "./Prescriptions/"+ appId + ".pdf";
			System.out.println("------------------------------------------"+name);
		%>
	
	
		<jsp:include page="Header.jsp"></jsp:include>
  
  <br><br><br>
	
	
		<div class="container">
			<div class="row justify-content-md-center">
				<div class="col-md-6 py-5 section-title">
					<h2 class="text-center ">Your Prescription is ready!</h2>
					<h2 class="text-center ">Download it from here</h2>
					<a href="<%=name%>" download class="checks">Download It Here</a>
				</div>
			</div>
		</div>
	</body>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</html>
