<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <a href="index.jsp" class="logo me-auto"><img src="assets/img/logo.JPG" alt="" class="img-fluid"></a>

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
         <li><a class="nav-link scrollto" href="index.jsp#hero">Home</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#about">About</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#services">Services</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#departments">Departments</a></li>
          <li><a class="nav-link scrollto" href="Search.jsp">Doctors</a></li>
          <li><a class="nav-link scrollto" href="index.jsp#contact">Contact</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
      
      <%
      	
	  	if(session.getAttribute("userObj") instanceof Doctor)
	  	{
	  		Doctor d=(Doctor) session.getAttribute("userObj");
	  		out.println("<a href=\"DoctorDashboard.jsp\" style=\" text-decoration:none \"  class=\"log-btn scrollto\"><span class=\"d-none d-md-inline\">Hi, "+ d.getFirstName()+"</span></a>");
	  	}
	  	else if(session.getAttribute("userObj") instanceof Patient)
	  	{
	  		Patient p=(Patient) session.getAttribute("userObj");
	  		out.println("<a href=\"PatientDashboard.jsp\" style=\" text-decoration:none \" class=\"log-btn scrollto\"><span class=\"d-none d-md-inline\">Hi, "+ p.getFirstName()+"</span></a>");
	  	}
	  	else if(session.getAttribute("userObj") instanceof Coordinator)
	  	{
	  		Coordinator c=(Coordinator) session.getAttribute("userObj");
	  		if(c.getId()==1)
	  		{
	  			out.println("<a href=\"AdminDashboard.jsp\" style=\" text-decoration:none \" class=\"log-btn scrollto\"><span class=\"d-none d-md-inline\">Hi, "+ c.getFirstName()+"</span></a>");
	  		}
	  		else
	  		{
	  			out.println("<a href=\"CoordinatorDashboard.jsp\" style=\" text-decoration:none \" class=\"log-btn scrollto\"><span class=\"d-none d-md-inline\">Hi, "+ c.getFirstName()+"</span></a>");
	  		}
  		}
	  	else
	  		out.println("<a href=\"Login.jsp\" style=\" text-decoration:none \" class=\"log-btn scrollto\"><span class=\"d-none d-md-inline\">Log in / Sign Up</span></a>");
      %>


    </div>
  </header><!-- End Header -->