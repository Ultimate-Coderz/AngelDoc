<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 

<html>
<head>
<title>Add Doctor</title>
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
    rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">

</head>

 	<%
		if (request.getSession(false) == null) 
		{
			response.sendRedirect("Login.jsp");
		}
	%>

	<%
		if (session.getAttribute("userObj") == null) {
			response.sendRedirect("Login.jsp");
		}
	%>

<body>

	<jsp:include page="Header.jsp"></jsp:include>
	<br><br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center ">Add Doctor</h2>

                    <form id="doctor" class="formSelect" action="AddDoctorServ" method="post">
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" placeholder="First" name="firstName" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">First Name</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" placeholder="Last" name="secondName" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">Last Name</label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" required id="floatingInput" placeholder="Email" name="email">
                        <label for="floatingInput">Email</label>
                    </div>     
                    
                     <div class="form-floating mb-3">
                        <input type="password" class="form-control" required id="floatingInput" placeholder="Specialization" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" >
                        <label for="floatingInput">Password</label>
                    </div>
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" required id="floatingInput" placeholder="Contact No." name="contactNum"> 
                        <label for="floatingInput">Contact No.</label>
                    </div> 
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" required id="floatingInput" placeholder="Specialization" name="special">
                        <label for="floatingInput">Specialization</label>
                    </div>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" placeholder="City" name="city">
                                <label for="floatingInput">City</label>
                            </div>
                        </div>
                        
                    </div>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Starting_Hour" name="startTime">
                                <label for="floatingInput">Starting_Hour</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Ending_Hour" name="endTime">
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