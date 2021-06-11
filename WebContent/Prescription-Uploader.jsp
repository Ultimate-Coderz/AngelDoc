<%@ page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title> Upload Prescription </title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/favicon.png" rel="apple-touch-icon">

    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
</head>
<body>

	<%
    		Doctor doc=(Doctor) session.getAttribute("userObj");
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
	
    %>

	<jsp:include page="Header.jsp"></jsp:include>
  
  <br><br><br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center"> Upload Prescription </h2><br>

                <form id="patient" class="formSelect rounded border p-5 " style="background-color: #f4f5f7; " action="PrescriptionUploader?appId=<%= request.getParameter("aid") %>" enctype="multipart/form-data" method="post" >
                    <div class="custom-file mb-3">
                        <label class="custom-file-label" for="customFile"><b> Prescription :</b></label>
                        <input class="form-control" id="formFileLg" type="file" onchange="displayImg()" name="file" />
                    </div>
                    <a href="./prescription.html" class="btn btn-primary m-4" target="_blank">Download Template</a>
                    <input type="submit" value="Submit" class="btn btn-primary m-4">
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>