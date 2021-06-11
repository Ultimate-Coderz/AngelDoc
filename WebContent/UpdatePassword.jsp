<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 


<!DOCTYPE html>

 

<html>

 

<head>
<meta charset="ISO-8859-1">
<title>Update Password</title>

 

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

<body>

<jsp:include page="Header.jsp"></jsp:include>
	<br><br><br>
	
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
	
		int role=0;	
	
			if(session.getAttribute("userObj") instanceof Coordinator)
			{
				Coordinator c=(Coordinator) session.getAttribute("userObj");
				
				if(c.getId()==1)
				{
					role=1;
				}
				else
				{
					role=2;
				}
				
			}
			else if(session.getAttribute("userObj") instanceof Doctor)
			{
				role=3;
			}
			else if(session.getAttribute("userObj") instanceof Patient)
			{
				role=4;
			}
	
	%>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center ">Change your password</h2>
                
                <form id="coordinator" class="formSelect text-center "
                    action="UpdatePasswordServ" >
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="floatingInput" required
                            placeholder="Email" name="old"> <label
                            for="floatingInput">Old</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password2" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                            placeholder="New Password" name="newP"> <label
                            for="floatingInput">New Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="repassword" onblur="check()" required
                            placeholder="Old Password" name="reNew"> <label
                            for="floatingInput">Retype Password</label>
                    </div>
                    	<input type="hidden" name="role" value="<%=role %>">
                    <input type="submit" value="Submit" class="btn btn-primary">
                    </form>
                    </div>
                    
            </div>
        </div>
</body>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="app.js"></script>
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