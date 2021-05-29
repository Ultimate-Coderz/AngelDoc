<%@page import="Hibernate.*"%>
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

    <title>Book Appointment</title>
  </head>
  <body>
    <%    
    
    if(request.getAttribute("Doctor")==null)
    {
      response.sendRedirect("Search.jsp");
    }
    else
    {
    	Doctor doc=(Doctor) request.getAttribute("Doctor");
  
  %>
  
 <jsp:include page="Header.jsp"></jsp:include>
  
  <br><br><br>
  
  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Appointment Form</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <%
        	session.setAttribute("docObj", doc);
        %>
        <form action="ValidateAppointment" method="post">
            <div class="modal-body">
              <div class="form-floating mb-3">
                <input type="text" class="form-control" required id="floatingInput" placeholder="Patient" name="patientName" >
                <label for="floatingInput">Patient</label>
            </div>

            <div class="row g-2">
	            <div class="col-md">
		            <div class="form-floating mb-3">
		                <input type="date" class="form-control" required id="floatingInput" placeholder="Date" name="date" >
		                <label for="floatingInput">Date</label>
		            </div>
	            </div>
                <div class="col-md">
                    <div class="form-floating mb-3">
                        <input type="time" class="form-control" required id="floatingInput" placeholder="Starting Time" name="startingTime" >
                        <label for="floatingInput">Starting Time</label>
                    </div>
                   
                </div>
            </div>
            <div class="alert alert-info text-center " role="alert">
            	Appointment will be of 30 minutes
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" value="300" id="floatingInput" placeholder="Charge" disabled >
                <label for="floatingInput">Charge</label>
            </div>
            </div>
            <div class="modal-footer">
              <div class="text-center">
                <input type="submit" value="Submit" class="btn btn-primary">
              </div>
            </div>
           
             
            </form>
      </div>
    </div>
  </div>
<!--// Onload Body //-->
  <div class="container ">
    <div class="row justify-content-md-center">		
        <div class="col-md-10 py-5 section-title">
            <h2 class="text-center mt-6 "> Book an Appointment </h2>
            <div class="row justify-content-center">	
        <div class="col-md-5 p-4 text-end ">
            <img src="./Image/Doctor.PNG" alt="Image" class="rounded img-thumbnail" style=" width:250px; " >                        
        </div>
        <div class="col-md-7 p-4 text-start ">
            <h4 class="text-dark mt-3 	 "><b>Doctor Name : </b><%= doc.getFirstName() + " " + doc.getLastName() %> </h4>
            <h4 class="text-dark"><b>Specialization : </b><%= doc.getDocSpec()%></h4>
            <h4 class="text-dark"><b>City : </b><%= doc.getCity() %></h4>
            <h4 class="text-dark "><b>Email : </b> <a href="mailto:<%= doc.geteMail() %>" class="text-dark text-decoration-none " ><%= doc.geteMail()  %></a></h4>
            <h4 class="text-dark"><b>Phone Number : </b> <a href="tel:*PhoneNumber*" class="text-dark text-decoration-none " ><%= doc.getContactNumber()  %></a></h4>
            <h4 class="text-dark"><b>Working hour : </b> <%= doc.getStartingHr() %> - <%= doc.getEndingHr() %> </h4>                    
        </div>
        
        <% 
        	
        	boolean correct=false;
        	
        	if(session.getAttribute("userObj")!=null)
        	{
        		correct=true;
        	}
        	
        	if(correct)
        	{
        		out.println("<div class=\"text-center\"><button type=\"button\" class=\"btn btn-primary my-5\" onclick=\"insertForm()\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">Book an Appointment</button></div>");
        	}
        	else
        	{
        		out.println("<div style='color:red; font-size:25px; ' scope='row' class='text-center'>"
						+ "<b>Please login first as a Patient</b>"+"</div>");
        		out.println("<div class=\"text-center\"><button type=\"button\" class=\"btn btn-primary my-1\" disabled onclick=\"insertForm()\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">Book an Appointment</button></div>");        		
        	}
        	
        %>
    </div><br><br>
        </div>
    </div>
    
</div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
   <%
   		} 
   %> 
  </body>
</html>