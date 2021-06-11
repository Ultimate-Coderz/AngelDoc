<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="ISO-8859-1">
<title>Add Patient</title>


<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
    rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossorigin="anonymous">

</head>

 	<%
		if (request.getSession(false) == null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>

	<%
		if (session.getAttribute("userObj") == null) 
		{
			response.sendRedirect("Login.jsp");
		}
	%>

<body>

	<jsp:include page="Header.jsp"></jsp:include>
	<br><br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center ">Add Patient</h2>
                <form id="patient" class="formSelect text-center" action="AddPatientServ" method="post">
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" required required pattern="[A-Za-z]{1,32}" title="Please provide a valid name"
                                    placeholder="First" name="firstName"> <label
                                    for="floatingInput">First Name</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" required required pattern="[A-Za-z]{1,32}" title="Please provide a valid name"
                                    placeholder="Last" name="secondName"> <label
                                    for="floatingInput">Last Name</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" id="floatingInput" required
                            placeholder="Age" name="age"> <label for="floatingInput">Age</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput"
                            placeholder="City" name="city"> <label
                            for="floatingInput">City</label>
                    </div>
                    <div class="form-floating">
                        <select class="form-select " id="floatingSelect" required
                            aria-label="Floating label select example" name="bloodGroup">
                            <option values="A">A+</option>
                            <option values="A">A-</option>
                            <option values="B">B+</option>
                            <option values="B">B-</option>
                            <option values="AB">AB+</option>
                            <option values="AB">AB-</option>
                            <option values="O">O+</option>
                            <option values="O">O-</option>
                        </select> <label for="floatingSelect">Blood Group</label>
                    </div>
                    <br>
                    <div class="form-floating">
                        <select class="form-select " id="floatingSelect" required
                            aria-label="Floating label select example" name="gender">
                            <option values="Male">Male</option>
                            <option values="Female">Female</option>
                            <option values="other">Other</option>
                        </select> <label for="floatingSelect">Gender</label>

                    </div>
                    <br>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="email" class="form-control" required
                                    id="floatingInput" placeholder="Email" name="email"> <label
                                    for="floatingInput">Email</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="floatingInput" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                                  required   placeholder="Password" name="password"> <label
                                    for="floatingInput">Password</label>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="floatingInput" required placeholder="Contact No." name="contactNum"> 
                        <label for="floatingInput">Contact No.</label>
                    </div> 
                    
                    <input type="hidden" name="role" value="patient"> <input
                        type="submit" value="Submit" class="btn btn-primary">
                </form>

            </div>
        </div>
    </div>
</body>

 

</html>