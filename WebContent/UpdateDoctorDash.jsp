<%@page import="Hibernate.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 

<html>
<head>
<title>Detail Form</title>
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
    rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">

</head>
<body>

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
				if (request.getSession(false) == null) {
					System.out.println("Session is null!!!");
					response.sendRedirect("Login.jsp");
				}
				%>
	
				<%
				if (session.getAttribute("userObj") == null) {
					response.sendRedirect("Login.jsp");
				}
					Doctor d=(Doctor)session.getAttribute("userObj");
			%>

			   <a href="Login.jsp" class="log-btn scrollto" style="text-decoration:none;"><span
                class="d-none d-md-inline">Hi, <%=d.getFirstName() %></span></a>

		</div>
	</header>
	<br><br>
<br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center ">Update Profile</h2>

                    <form id="doctor" class="formSelect" action="UpdateDoctorDash" method="post">
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" id="floatingInput" placeholder="First" name="firstName" value="<%=d.getFirstName() %>" >
                                <label for="floatingInput">First Name</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" id="floatingInput" placeholder="Last" name="lastName" value="<%=d.getLastName() %>" >
                                <label for="floatingInput">Last Name</label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" required id="floatingInput" placeholder="Email" name="email" value="<%=d.geteMail() %>" >
                        <label for="floatingInput">Email</label>
                    </div>     
                    
                     <div class="form-floating mb-3">
                        <input type="password" class="form-control" required id="floatingInput" placeholder="Specialization" name="password" value="<%=d.getPassword() %>" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
                        <label for="floatingInput">Password</label>
                    </div>
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" required placeholder="Contact No." name="contactNum" value="<%=d.getContactNumber()%>" > 
                        <label for="floatingInput">Contact No.</label>
                    </div> 
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" required placeholder="Specialization" name="special" value="<%=d.getDocSpec() %>" >
                        <label for="floatingInput">Specialization</label>
                    </div>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" required placeholder="City" name="city" value="<%=d.getCity() %>" >
                                <label for="floatingInput">City</label>
                            </div>
                        </div>
                        
                    </div>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Starting_Hour" name="startTime" value="<%=d.getStartingHr() %>" >
                                <label for="floatingInput">Starting_Hour</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Ending_Hour" name="endTime" value="<%=d.getEndingHr() %>" >
                                <label for="floatingInput">Ending_Hour</label>
                            </div>
                        </div>
                    </div>
                    <input type="submit" value="Submit" class="btn btn-primary">
                </form>

            </div>
        </div>
    </div>

    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

 

</body>
</html>