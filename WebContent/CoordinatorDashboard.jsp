<%@page import="Hibernate.*"%>
<%@page import="java.util.List"%>
<%@page import="dao.CoordinatorDashboardDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="Login.jsp"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Angel Doc</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Other CSS Files -->

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">

<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">

<!-- Main CSS File -->
<link href="assets/css/dashboard.css" rel="stylesheet">
</head>

<body style="background: #f1f7fd;">

	<%
		
		if(session==null)
		{
			response.sendRedirect("Login.jsp");
		}
		
	%>

	<%
		Coordinator c=(Coordinator) session.getAttribute("userObj");
	%>

	<!-- ======= Header =======-->
	<header id="header">
		<div class="d-flex flex-column">
			<div class="m-2"></div>
			
			<div class="profile">			
				<img src="<%= c.getProfile() %>" alt=""
					class="img-fluid rounded-circle">
				<h1 class="text-dark"><%= c.getFirstName() +" "+ c.getLastName() %></h1>
				<h1 class="text-light"
					style="font-size: smaller; color: rgb(152, 175, 219) !important;">Coordinator</h1>
			</div>

			<nav id="navbar" class="nav-menu navbar ">
				<ul>
					<li><a href="index.jsp" class="nav-link "><i
							class="bx bx-home"></i> <span> Home</span></a></li>
					<li><a href="UpdateCoordinatorDash.jsp" class="nav-link "><i class="bx bx-user"></i>
							<span> Update Profile</span></a></li>
					<li><a href="UpdatePassword.jsp" class="nav-link "><i class='bx bx-key'></i>
							<span> Update Password</span></a></li>
					<li><a href="LogoutServ" class="nav-link"><i class='bx bx-power-off'></i>
							<span> Log Out</span></a></li>
				</ul>
			</nav>
			<!-- .nav-menu -->			
		</div>
		
		<div class="text-center m4">
		<div class="text-center text-dark mt-6"><br>
				<br><br><br><small><i class="far fa-copyright"></i> Copyright AngelDoc.com</small>
			</div>
		</div>
	</header>
	<div id="main">
		<section id="counts" class="counts">
			<div class="container">
				<div class="row">
					<%
					CoordinatorDashboardDAO pdao = new CoordinatorDashboardDAO();
					%>



					<div class="col-md-4">
						<div class="count-box">
							<i class="fas fa-user-md"></i> <span data-purecounter-start="0"
								data-purecounter-end=<%=pdao.getDoctorCount()%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Doctors</p>
						</div>
					</div>



					<div class="col-md-4 mt-5 mt-md-0">
						<div class="count-box">
							<i class="fas fa-file-medical-alt"></i> <span
								data-purecounter-start="0"
								data-purecounter-end=<%=pdao.getAppointmentCount()%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Appointments</p>
						</div>
					</div>



					<div class="col-md-4 mt-5 mt-md-0">
						<div class="count-box">
							<i class="fas fa-user-injured"></i> <span data-purecounter-start="0"
								data-purecounter-end=<%=pdao.getPatientCount()%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Patients</p>
						</div>
					</div>



				</div>



			</div>
		</section>
		<!-- End Counts Section -->



		<%
		List<Appointment> appList = pdao.getAppointments();
		%>

		<section id="appointment" class="appointments">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="section-dash appointment col-md-12 p-4">
						<div class="row">
							<div class="col-4"></div>
							<div class="col-4">
								<h1 class="text-light text-center pb-3">Appointments</h1>
							</div>
						</div>
						<%
						if (appList.isEmpty()) {
						%>
						<hr style="color: black;" class="text-center">
						<h4 class="text-center text-light">No appointments</h4>
						<%
						} else {
						%>
						<table class="table table-responsive table-sm table-hover">
							<thead>
								<tr>
									<th class="text-center" scope="col">ID</th>
									<th class="text-center" scope="col">PID</th>
									<th class="text-center" scope="col">DR ID</th>
									<th class="text-center" scope="col">Date</th>
									<th class="text-center" scope="col">Slot</th>
									<th class="text-center" scope="col">Charges</th>
									<th class="text-center" scope="col">Details</th>
								</tr>
							</thead>
							<%
							for (Appointment a : appList) 
							{
							%>
							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=a.getAppId()%></th>
									<td class="text-center text-light"><%=a.getPatient().getpId()%></td>
									<td class="text-center text-light"><%=a.getDoctor().getDocId()%></td>
									<td class="text-center text-light"><%=a.getSqlDate()%></td>
									<td class="text-center text-light"><%=a.getStartingTime() + " - " + a.getEndingTime()%></td>
									<td class="text-center text-light"><%=a.getCharges()%></td>
									<td class="text-center text-light"><a href="AppointInfoServ?id=<%=a.getAppId()%>"><i class='bx bx-info-circle mt-1'></i></a></td>
								</tr>
							</tbody>
							<%
							}
							%>
						</table>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</section>
		<%
		List<Doctor> docList = pdao.getDoctors();
		%>
		<section id="doctor" class="doctors">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="section-dash doctor col-md-12 p-4">
						<div class="row">
							<div class="col-4"></div>
							<div class="col-4">
								<h1 class="text-light text-center pb-3">Doctors</h1>
							</div>
							<div class="col-4">
								<a href="AddDoctor.jsp" class="btn btn-primary float-end ">Add Doctor</a>
							</div>
						</div>
						<%
						if (docList.isEmpty()) {
						%>
						<hr style="color: black;" class="text-center">
						<h4 class="text-center text-light">No Doctors</h4>
						<%
						} else {
						%>
						<table class="table table-responsive table-sm table-hover">
							<thead>
								<tr>
									<th class="text-center" scope="col">ID</th>
									<th class="text-center" scope="col">Name</th>
									<th class="text-center" scope="col">Specialization</th>
									<th class="text-center" scope="col">Working Time</th>
									<th class="text-center" scope="col">Details</th>
									<th class="text-center" scope="col">Delete</th>
								</tr>
							</thead>
							<%
							for (Doctor a : docList) {
							%>
							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=a.getDocId()-3000000+1%></th>
									<td class="text-center text-light"><%=a.getFirstName() + " " + a.getLastName()%></td>
									<td class="text-center text-light"><%=a.getDocSpec()%></td>
									<td class="text-center text-light"><%=a.getStartingHr()+" - "+a.getEndingHr()%></td>
									<td class="text-center text-light"><a
                                        href="UserDetails.jsp?userId=<%=a.getDocId() %>"><i
                                            class='bx bx-info-circle mt-1'></i></a></td>
									<td class="text-center text-light"><a href="DeleteUserServ?id=<%=a.getDocId()%>"><i
											class="far fa-times-circle"></i></a></td>
								</tr>
							</tbody>
							<%
							}
							%>
						</table>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</section>

		<%
		List<Patient> patList = pdao.getPatients();
		%>

		<section id="patient" class="patients">
			<div class="container-fluid ">
				<div class="row justify-content-md-center">
					<div class="section-dash patient col-md-12  p-4">

						<div class="row">
							<div class="col-4"></div>
							<div class="col-4">
								<h1 class="text-light text-center pb-3">Patients</h1>
							</div>
							<div class="col-4">
								<a href="AddPatient.jsp" class="btn btn-primary float-end ">Add Patient</a>
							</div>
						</div>
						<%
						if (patList.isEmpty()) {
						%>
						<hr style="color: black;" class="text-center">
						<h4 class="text-center text-light">No Patients</h4>
						<%
						} else {
						%>
						<table class="table table-responsive table-sm table-hover">
							<thead>
								<tr>
									<th class="text-center" scope="col">ID</th>
									<th class="text-center" scope="col">Name</th>
									<th class="text-center" scope="col">Contact Number</th>
									<th class="text-center" scope="col">City</th>
									<th class="text-center" scope="col">Gender</th>
									<th class="text-center" scope="col">Details</th>
									<th class="text-center" scope="col">Delete</th>
								</tr>
							</thead>
							<%
							for (Patient a : patList) {
							%>
							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=a.getpId()-5000000+1%></th>
									<td class="text-center text-light"><%=a.getFirstName() + " " + a.getLastName()%></td>
									<td class="text-center text-light"><%=a.getContactNumber()%></td>
									<td class="text-center text-light"><%=a.getCity()%></td>
									<td class="text-center text-light"><%=a.getpGender()%></td>
									<td class="text-center text-light"><a
                                        href="UserDetails.jsp?userId=<%=a.getpId() %>"><i
                                            class='bx bx-info-circle mt-1'></i></a></td>
									<td class="text-center text-light"><a href="DeleteUserServ?id=<%=a.getpId()%>"><i
											class="far fa-times-circle"></i></a></td>
								</tr>
							</tbody>
							<%
							}
							%>
						</table>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</section>
	</div>
	<br>
	<br>
	<div id="preloader"></div>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/purecounter/purecounter.js"></script>
	<script src="https://kit.fontawesome.com/64d58efce2.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
		crossorigin="anonymous"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>

</html>