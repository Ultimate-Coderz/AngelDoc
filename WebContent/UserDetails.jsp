<%@page import="Hibernate.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

 

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

 

  <title>AngleDoc</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

 

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/favicon.png" rel="apple-touch-icon">

 

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

 

  <!-- Other CSS Files -->
  <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

 

  <!-- Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

 

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

 

    <title>Details</title>
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
  %>
  
<%
int userId = Integer.parseInt(request.getParameter("userId"));
UsersDAO udao = new UsersDAO();
int role = udao.getRole(userId);
Coordinator c ; Doctor d ; Patient p ;
String roleName = "";
if(role == 2)
{
    roleName = "Coordinator";
    c = new CoordinatorDAO().find(userId);
    %>
    
    <div class="container ">
    <div class="row justify-content-md-center">        
        <div class="col-md-10 py-5 section-title">
            <h2 class="text-center mt-6 "> Coordinator Details </h2>
            <div class="row justify-content-center">    
        <div class="col-md-5 p-4 text-end my-auto ">
            <img src="./Image/Doctor.PNG" alt="Image" class="rounded img-thumbnail w-75" >                        
        </div>
        <div class="col-md-7 p-4 text-start justify-content-center mt-4">
            <h4 class="text-dark mt-3      "><b>Name : </b><%= c.getFirstName() + " " + c.getLastName() %> </h4>
            <h4 class="text-dark"><b>Id : </b> <%= c.getId() %>  </h4> 
            <h4 class="text-dark"><b>Designation : </b><%= c.getDesignation()%></h4>
            <h4 class="text-dark"><b>Phone Number : </b> <a href="tel:*PhoneNumber*" class="text-dark text-decoration-none " ><%= c.getContactNumber()  %></a></h4>
            <h4 class="text-dark "><b>Email : </b> <a href="mailto:<%=c.getEMail() %>" class="text-dark text-decoration-none " ><%= c.getEMail()  %></a></h4>
             </div>
        <a href="AdminDashboard.jsp"><button type="submit" class="btn btn-primary my-5">Back to the Dashboard</button></a>
    </div><br><br>
        </div>
    </div>
    
</div>
    
    <%
}
else if(role == 3)
{
    roleName = "Doctor";
    d = new DoctorDAO().find(userId);    
    
    %>
    
    <div class="container ">
    <div class="row justify-content-md-center">        
        <div class="col-md-10 py-5 section-title">
            <h2 class="text-center mt-6 "> Doctor Details </h2>
            <div class="row justify-content-center">    
        <div class="col-md-5 p-4 text-end my-auto ">
            <img src="./Image/Doctor.PNG" alt="Image" class="rounded img-thumbnail w-75  " >                        
        </div>
        <div class="col-md-7 p-4 text-start justify-content-center">
            <h4 class="text-dark mt-3      "><b>Name : </b><%= d.getFirstName() + " " + d.getLastName() %> </h4>
            <h4 class="text-dark"><b>Id : </b> <%= d.getDocId() %>  </h4> 
            <h4 class="text-dark"><b>Specialization : </b><%= d.getDocSpec()%></h4>
            <h4 class="text-dark"><b>City : </b><%= d.getCity() %></h4>
            <h4 class="text-dark "><b>Email : </b> <a href="mailto:<%= d.geteMail() %>" class="text-dark text-decoration-none " ><%= d.geteMail()  %></a></h4>
            <h4 class="text-dark"><b>Phone Number : </b> <a href="tel:*PhoneNumber*" class="text-dark text-decoration-none " ><%= d.getContactNumber()  %></a></h4>
            <h4 class="text-dark"><b>Working hour : </b> <%= d.getStartingHr() %> - <%= d.getEndingHr() %> </h4>                    
        </div>
        <a href="AdminDashboard.jsp"><button type="submit" class="btn btn-primary my-5">Back to the Dashboard</button></a>
    </div><br><br>
        </div>
    </div>
    
</div>
    
    
    <%
}
else {
    roleName = "Patient";
    p = new PatientDAO().find(userId);
    
    %>
    
    <div class="container ">
    <div class="row justify-content-md-center">        
        <div class="col-md-10 py-5 section-title">
            <h2 class="text-center mt-6 "> Patient Details </h2>
            <div class="row justify-content-center">    
        <div class="col-md-5 p-4 text-end my-auto ">
            <img src="./Image/Doctor.PNG" alt="Image" class="rounded img-thumbnail w-75" >                        
        </div>
        <div class="col-md-7 p-4 text-start ">
            <h4 class="text-dark mt-3"><b>Name : </b><%= p.getFirstName() + " " + p.getLastName() %> </h4>
            <h4 class="text-dark"><b>Id : </b> <%= p.getpId() %>  </h4> 
            <h4 class="text-dark"><b>Age : </b> <%= p.getpAge() %>  </h4> 
            <h4 class="text-dark"><b>Blood Group : </b><%= p.getpBloodGrp()%></h4>
            <h4 class="text-dark"><b>City : </b><%= p.getCity() %></h4>
            <h4 class="text-dark "><b>Email : </b> <a href="mailto:<%= p.getEMail() %>" class="text-dark text-decoration-none " ><%= p.getEMail()  %></a></h4>
            <h4 class="text-dark"><b>Phone Number : </b> <a href="tel:*PhoneNumber*" class="text-dark text-decoration-none " ><%= p.getContactNumber()  %></a></h4>
            <h4 class="text-dark"><b>Gender : </b> <%= p.getpGender() %>  </h4>                    
        </div>
        <a href="AdminDashboard.jsp"><button type="submit" class="btn btn-primary my-5">Back to the Dashboard</button></a>
    </div><br><br>
        </div>
    </div>
    
</div>
    
    <%
}
%>

  
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
  
</body>
</html>