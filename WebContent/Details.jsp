	<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title> Detail Form </title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon"> 
    <link href="assets/img/favicon.png" rel="apple-touch-icon">
    
     <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossorigin="anonymous">
    
    <style>
        .formSelect{
            display: none;
        }
        .activated{
            display: block !important;
        }
    </style>

    <script>

        function switchForm(){
            let chk = document.getElementById("floatingSelect").value;
            if(chk=="2"){
                document.getElementById("doctor").classList.add("activated");
                document.getElementById("patient").classList.remove("activated");
                document.getElementById("management").classList.remove("activated");
            }else if(chk=="3"){
                document.getElementById("patient").classList.add("activated");
                document.getElementById("doctor").classList.remove("activated");
                document.getElementById("management").classList.remove("activated");
            }
        }
    </script>
</head>
<body>

	<jsp:include page="Header.jsp"></jsp:include>
	
	<%
	
		String name=request.getParameter("uname").trim();
		int num=name.indexOf(" ");
		String firstName="";
		String lastName="";
		if(num==-1)
		{
			firstName=name;
		}
		else
		{
			firstName=name.substring(0, num);
			lastName=name.substring(num).trim();
		}
		
	%>
  
  <br><br><br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center "> Complete Your Profile </h2>

                <div class="select m-5">
                    <div class="form-floating">
                        <select class="form-select" id="floatingSelect" required aria-label="Floating label select example" onchange="switchForm()">
                            <option selected>Select One</option>
                          <option value="2">Doctor</option>
                          <option value="3">Patient</option>
                        </select>
                        <label for="floatingSelect">Who are You?</label>
                      </div>
                </div>

                <form id="patient" class="formSelect" action="DetailsServ" method="post">
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="floatingInput" required value="<%= firstName %>" name="First" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">First Name</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" placeholder="Last" value="<%= lastName %>" name="Last" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">Last Name</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" required id="floatingInput" placeholder="Age" name="Age">
                        <label for="floatingInput">Age</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" required id="floatingInput" placeholder="City" name="City">
                        <label for="floatingInput">City</label>
                    </div>
                    <div class="form-floating">
                        <select class="form-select " id="floatingSelect" required aria-label="Floating label select example" name="BloodGroup">
                            <option values="A">A+</option>
                            <option values="A">A-</option>
                            <option values="B">B+</option>
                            <option values="B">B-</option>
                            <option values="AB">AB+</option>
                            <option values="AB">AB-</option>
                            <option values="O">O+</option>
                            <option values="O">O-</option>
                        </select>
                        <label for="floatingSelect">Blood Group</label>
                        
                    </div> <br>
                    
                    <div class="form-floating">
                        <select class="form-select " id="floatingSelect" required aria-label="Floating label select example" name="Gender">
                            <option values="Male">Male</option>
                            <option values="Female">Female</option>
                            <option values="other">Other</option>
                           
                        </select>
                        <label for="floatingSelect">Gender</label>
                        
                    </div> <br>
                    
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" required id="floatingInput" placeholder="Cotact" name="contactNum">
                        <label for="floatingInput">Contact</label>
                    </div>
                    
                   <div class="row g-2">
                    <div class="col-md">
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" required id="floatingInput"  value="<%= request.getParameter("email").trim() %>"  name="Email">
                            <label for="floatingInput">Email</label>
                        </div>
                        </div>
                        <div class="col-md">
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" required id="floatingInput" value="<%= request.getParameter("pass").trim() %>" name="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" >
                            <label for="floatingInput">Password</label>
                        </div>
                    </div>
                    </div>
                    <input type="hidden" name="role" value=4>
                    <input type="submit" value="Submit" class="btn btn-primary">
                </form>

                <form id="doctor" class="formSelect" action="DetailsServ" method="post">
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" value="<%= firstName %>" name="First" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">First Name</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" required id="floatingInput" placeholder="Last" value="<%= lastName %>"  name="Last" required pattern="[A-Za-z]{1,32}" title="Please provide a valid name" >
                                <label for="floatingInput">Last Name</label>
                            </div>
                        </div>
                    </div>
                    
                    
                     <div class="row g-2">
                    <div class="col-md">
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" required id="floatingInput" value="<%= request.getParameter("email") %>" name="Email"> 
                            <label for="floatingInput">Email</label>
                        </div>
                        </div>
                        <div class="col-md">
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" required id="floatingInput" value="<%= request.getParameter("pass") %>" name="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" >
                            <label for="floatingInput">Password</label>
                        </div>
                    </div>
                    </div>
                    
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" required id="floatingInput" placeholder="Specialization" name="Specialization">
                        <label for="floatingInput">Specialization</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" required id="floatingInput" placeholder="City" name="City">
                        <label for="floatingInput">City</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control" required id="floatingInput" placeholder="Cotact" name="contactNum">
                        <label for="floatingInput">Contact</label>
                    </div>
                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Starting_Hour" name="Starting_Hour">
                                <label for="floatingInput">Starting_Hour</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating mb-3">
                                <input type="time" class="form-control" required id="floatingInput" placeholder="Ending_Hour" name="Ending_Hour">
                                <label for="floatingInput">Ending_Hour</label>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="role" value=3>

                    <input type="submit" value="Submit" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>
    
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>