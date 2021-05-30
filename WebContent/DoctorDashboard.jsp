<%@page import="Hibernate.*"%>
<%@page import="java.util.List"%>
<%@page import="dao.DoctorDashboardDAO"%>
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
	if (session == null) {
		response.sendRedirect("Login.jsp");
	}
	%>

	<%
	Doctor d = (Doctor) session.getAttribute("userObj");
	%>

	<!-- ======= Header =======-->
	<header id="header">
		<div class="d-flex flex-column">
			<div class="m-2"></div>

			<div class="profile">
				<img src="<%=d.getProfile()%>" alt=""
					class="img-fluid rounded-circle">
				<h1 class="text-dark"><%=d.getFirstName() + " " + d.getLastName()%></h1>
				<h1 class="text-light"
					style="font-size: smaller; color: rgb(152, 175, 219) !important;">Doctor</h1>
			</div>

			<nav id="navbar" class="nav-menu navbar ">
				<ul>
					<li><a href="index.jsp" class="nav-link "><i
							class="bx bx-home"></i> <span> Home</span></a></li>
					<li><a href="UpdateDoctorDash.jsp" class="nav-link "><i
							class="bx bx-user"></i> <span> Update Profile</span></a></li>
					<li><a href="UpdatePassword.jsp" class="nav-link "><i
							class='bx bx-key'></i> <span> Update Password</span></a></li>
					<li><a href="<%=d.getRoom()%>" target="_blank"
						class="nav-link"><i class='bx bx-video'></i> <span>Join
								Room</span></a></li>
					<li><a href="LogoutServ" class="nav-link"><i
							class='bx bx-power-off'></i> <span> Log Out</span></a></li>
				</ul>
			</nav>
			<!-- .nav-menu -->
		</div>

		<div class="text-center ">
			<div class="text-center text-dark mt-1">
				<br> <br> <small><i class="far fa-copyright"></i> Copyright AngelDoc.com</small>
			</div>
		</div>
	</header>

	<%
	DoctorDashboardDAO ddao = new DoctorDashboardDAO();
	ddao.updateStatus(d.getDocId());
	%>
	<div id="main">
		<section id="counts" class="counts">
			<div class="container">

				<div class="row">



					<div class="col-md-4">
						<div class="count-box">
							<i class="fas fa-user-md"></i> <span data-purecounter-start="0"
								data-purecounter-end=<%=ddao.getPatientsMet(d.getDocId())%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Patients Met</p>
						</div>
					</div>



					<div class="col-md-4 mt-5 mt-md-0">
						<div class="count-box">
							<i class="fas fa-file-medical-alt"></i> <span
								data-purecounter-start="0"
								data-purecounter-end=<%=ddao.getPendingCount(d.getDocId())%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Pending Appointments</p>
						</div>
					</div>



					<div class="col-md-4 mt-5 mt-md-0">
						<div class="count-box">
							<i class="fas fa-user"></i> <span data-purecounter-start="0"
								data-purecounter-end=<%=ddao.getPastCount(d.getDocId())%>
								data-purecounter-duration="1" class="purecounter"></span>
							<p>Total Successful Appointments</p>
						</div>
					</div>



				</div>



			</div>
		</section>
		<!-- End Counts Section -->

		<%
		List<Appointment> list = ddao.getUpcomingAppointments(d.getDocId());
		%>



		<section id="appointment" class="appointments">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="section-dash appointment col-md-12 p-4">
						<div class="row">
							<div class="col-4"></div>
							<div class="col-12">
								<h1 class="text-light text-center pb-3">Upcoming
									Appointments</h1>
							</div>
						</div>
						<%
						if (list.isEmpty()) {
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
									<th class="text-center" scope="col">Patient ID</th>
									<th class="text-center" scope="col">Patient Name</th>
									<th class="text-center" scope="col">Date</th>
									<th class="text-center" scope="col">Slot</th>
									<th class="text-center" scope="col">Cancel</th>
									<th class="text-center" scope="col">Details</th>
								</tr>
							</thead>
							<%
							for (Appointment a : list) {
							%>
							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=a.getAppId()%></th>
									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=a.getAppId()%>"><%=a.getPatient().getpId()%></a></td>
									<td class="text-center text-light"><%=a.getPatient().getFirstName()%></td>
									<td class="text-center text-light"><%=a.getSqlDate()%></td>
									<td class="text-center text-light"><%=a.getStartingTime() + " - " + a.getEndingTime()%></td>

									<td class="text-center text-light"><a
										onclick="
                                          Notiflix.Confirm.Show(
                                                    'Confirm Cancel',
                                                    'Do you want to cancel this appointment?',
                                                    'Yes', 'No',
                                                    function(){
                                                      window.location.href = 'RejectAppointmentServ?appId=<%=a.getAppId()%>'; },
                                                    function(){
                                                     
                                                    });"
										href="#"><i class="far fa-times-circle mt-1"></i></a></td>
									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=a.getAppId()%>"><i
											class='bx bx-info-circle mt-1'></i></a></td>
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
		<!-- Upcoming appointments end
    -->


		<%
		List<Appointment> pendingList = ddao.getPendingAppointments(d.getDocId());
		%>

		<!-- Pending appointments-->
		<section id="doctor" class="doctors">
			<div class="container">
				<div class="row justify-content-md-center">
					<div class="section-dash doctor col-md-12 p-4">
						<div class="row">
							<div class="col-4"></div>
							<div class="col-12">
								<h1 class="text-light text-center pb-3">Pending
									Appointments</h1>
							</div>
						</div>
						<%
						if (pendingList.isEmpty()) {
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
									<th class="text-center" scope="col">Patient Name</th>
									<th class="text-center" scope="col">Date</th>
									<th class="text-center" scope="col">Slot</th>
									<th class="text-center" scope="col">Approve App.</th>
									<th class="text-center" scope="col">Reject App.</th>
									<th class="text-center" scope="col">Details</th>
								</tr>
							</thead>
							<%
							for (Appointment a : pendingList) {
							%>

							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=a.getAppId()%></th>
									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=a.getAppId()%>"><%=a.getPatient().getFirstName() + " " + a.getPatient().getLastName()%></a></td>
									<td class="text-center text-light"><%=a.getSqlDate()%></td>
									<td class="text-center text-light"><%=a.getStartingTime() + " - " + a.getEndingTime()%></td>
									<td class="text-center text-light"><a
										onclick="
                                          Notiflix.Confirm.Show(
                                                    'Confirm Approve',
                                                    'Do you want to approve this appointment?',
                                                    'Yes', 'No',
                                                    function(){
                                                      window.location.href = 'ApproveAppointmentServ?appId=<%=a.getAppId()%>'; },
                                                    function(){
                                                     
                                                    });"
										href="#"><i class="fas fa-user-edit"></i></a></td>

									<td class="text-center text-light"><a
										onclick="
                                          Notiflix.Confirm.Show(
                                                    'Confirm Reject',
                                                    'Do you want to reject this appointment?',
                                                    'Yes', 'No',
                                                    function(){
                                                      window.location.href = 'RejectAppointmentServ?appId=<%=a.getAppId()%>'; },
                                                    function(){
                                                     
                                                    });"
										href="#"><i class="far fa-times-circle mt-1"></i></a></td>

									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=a.getAppId()%>"><i
											class='bx bx-info-circle mt-1'></i></a></td>

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
		List<Appointment> pastList = ddao.getPastAppointments(d.getDocId());
		%>

		<!-- Past Appointments -->
		<section id="appointment" class="appointments">
			<div class="container-fluid ">
				<div class="row justify-content-md-center">
					<div class="section-dash patient col-md-12  p-4">

						<div class="row">
							<div class="col-4"></div>
							<div class="col-12">
								<h1 class="text-light text-center pb-3">Past Appointments</h1>
							</div>
						</div>

						<%
						if (pastList.isEmpty()) {
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
									<th class="text-center" scope="col">Patient ID</th>
									<th class="text-center" scope="col">Patient Name</th>
									<th class="text-center" scope="col">Slot</th>
									<th class="text-center" scope="col">Charges</th>
									<th class="text-center" scope="col">Upload Prescription</th>
									<th class="text-center" scope="col">Details</th>
								</tr>
							</thead>
							<%
							for (Appointment aa : pastList) {
							%>
							<tbody>
								<tr>
									<th class="text-center" scope="row"><%=aa.getAppId()%></th>
									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=aa.getAppId()%>"><%=aa.getPatient().getpId()%></a></td>
									<td class="text-center text-light"><%=aa.getPatient().getFirstName() + " " + aa.getPatient().getLastName()%></td>
									<td class="text-center text-light"><%=aa.getSqlDate() + " " + aa.getStartingTime() + " - " + aa.getEndingTime()%></td>
									<td class="text-center text-light"><%=aa.getCharges()%></td>
									<td class="text-center text-light"><a
										href="Prescription-Uploader.jsp?aid=<%=aa.getAppId()%>"><i
											class="fas fa-user-edit"></i></a></td>
									<td class="text-center text-light"><a
										href="AppointInfoServ?id=<%=aa.getAppId()%>"><i
											class='bx bx-info-circle mt-1'></i></a></td>
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
	
	<script src="assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js"></script>
  <script src="assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js"></script>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
	<script type="text/javascript">
		Notiflix.Confirm.Init({ borderRadius:"10px", titleFontSize:"20px", okButtonBackground:"#c63232", titleColor:"#c63232" });
	</script>

</body>

</html>
