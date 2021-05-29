<%@page import="Hibernate.Doctor"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Hibernate.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title> Appointment Information </title>
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">
    <link href="assets/img/favicon.png" rel="apple-touch-icon">

</head>
<body>

    <%
    		Doctor doc=(Doctor) session.getAttribute("userObj");
    		Appointment app=(Appointment) request.getAttribute("App");
    %>

   <jsp:include page="Header.jsp"></jsp:include>
	<br><br>
<br>

	<%
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=sdf.parse(app.getSqlDate().toString() + " " + app.getStartingTime()+":00" ); 
		Date d1=new Date();
		long temp=d.getTime()-d1.getTime();
		long diff=(temp/(1000*60));		
	%>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center "> Appointment </h2><br>
                <table class="table table-responsive table-borderless border" style="background-color: #f4f5f7;" >
                    <tbody>
                        <tr>
                          <th class="p-3 pl-6" scope="row"> Appointment ID</th>
                          <td class="p-3">:</td>
                          <td class="p-3"><%=app.getAppId() %></td>
                        </tr>
                        <tr>
                          <th class="p-3" scope="row">Doctor Name </th>
                          <td class="p-3">:</td>
                          <td class="p-3"><%=app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName() %></td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">Patient Name </th>
                            <td class="p-3">:</td>
                            <td class="p-3"><%= app.getPatient().getFirstName() + " " + app.getPatient().getLastName() %></td>
                        </tr>
                        <tr>
                            <th class="p-3" scope="row">Appointment Date</th>
                            <td class="p-3">:</td>
                            <td class="p-3"><%= app.getSqlDate() %></td> 
                        </tr>
                        <tr>
                            <th class="p-3" scope="row"> Starting Time</th>
                            <td class="p-3">:</td>
                            <td class="p-3"><%=app.getStartingTime() %></td>
                          </tr>
                          <tr>
                            <th class="p-3" scope="row">Ending time</th>
                            <td class="p-3">:</td>
                            <td class="p-3"><%=app.getEndingTime() %></td>
                          </tr>
                          <tr>
                              <th class="p-3" scope="row">Status</th>
                              <td class="p-3">:</td>
                              <td class="p-3"><%=app.getStatus() %></td> 
                          </tr>
                          <%
                          	if(diff<-30)
                          	{
                          %>
	                          <tr>
	                             <th  class="p-3"  scope="row">Download Prescription</th>
	                              <td  class="p-3" >:</td>
	                              <td  class="p-3" > <a href="PrescriptionDownloader.jsp?appId=<%=app.getAppId() %>" class="">Prescription</a> </td> <!-- Need to work here  -->
	                          </tr>
                          <%
                          	}
                          %>
                          <tr>
                            <th class="p-3" scope="row">Charges</th>
                            <td class="p-3">:</td>
                            <td class="p-3"> <%= app.getCharges() %> </td>
                        </tr>
                      </tbody>
                </table>
                
                <%
                	
            		if(diff<10 && diff>-15)
            		{
            			System.out.println("1");	
            			out.println("<div class=\"row\">");
	            			out.println("<div class=\"col-6\">");
	            				out.println("<a href=\""+ app.getDoctor().getRoom() +"\" target=\"_blank\" class=\"btn btn-outline-primary w-50 \">Join Room</a>");
	            			out.println(" </div>");
            				out.println("<div class=\"col-6\">");
            					out.println("<a href=\"DoctorDashboard.jsp\" ><input type=\"button\" value=\"Close\" class=\"btn btn-danger w-50\" ></a>");
            				out.println("</div>");                   
            			out.println("</div>");
            		}
            		else
            		{
            			if(diff<-30 && diff>-60)
                		{
            				System.out.println("2");
                			out.println("<div class=\"row\">");
            				out.println("<div class=\"col-6\">");
            					out.println("<a href=\"Prescription-Uploader.jsp?aid="+app.getAppId()+"\" class=\"btn btn-primary\">Add Prescription</a>");
            				out.println("</div>");
            				out.println("<div class=\"col-6\">");
            					out.println("<a href=\"DoctorDashboard.jsp\" ><input type=\"button\" value=\"Close\" class=\"btn btn-danger w-50\" ></a>");
            				out.println("</div>");                   
            				out.println("</div>");
                			
                		}
            			else
            			{
            				System.out.println("3");
	            			out.println("<div class=\"row justify-content-center \">");
		            			out.println("<div class=\"col-6\">");
		            				out.println("<a href=\"DoctorDashboard.jsp\" ><input type=\"button\" value=\"Close\" class=\"btn btn-danger w-50\" ></a>");
		            			out.println("</div>");                    
	            			out.println("</div>");
            			}
            		}
            		
                %>                
            </div>
        </div>
    </div>
    
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>