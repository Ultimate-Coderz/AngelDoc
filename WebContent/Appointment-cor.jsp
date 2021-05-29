<%@page import="Hibernate.Coordinator"%>
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
    		Appointment app=(Appointment) request.getAttribute("App");
    %>
    
    <jsp:include page="Header.jsp"></jsp:include>
	<br><br>
<br>

    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center "> Appointment </h2><br>
                <table class="table table-responsive table-borderless border" style="background-color: #f4f5f7;">
                    <tbody>
                        <tr>
                          <th class="p-3"  scope="row"> Appointment ID </th>
                          <td  class="p-3" >:</td>
                          <td  class="p-3" ><%=app.getAppId() %></td>
                        </tr>
                        <tr>
                          <th  class="p-3"  scope="row">Doctor Name</th>
                          <td  class="p-3" >:</td>
                          <td  class="p-3" ><%=app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName() %></td>
                        </tr>
                        <tr>
                            <th  class="p-3"  scope="row">Patient Name </th>
                            <td  class="p-3" >:</td>
                            <td  class="p-3" ><%= app.getPatient().getFirstName() + " " + app.getPatient().getLastName() %></td>
                        </tr>
                        <tr>
                            <th  class="p-3"  scope="row">Appointment Date</th>
                            <td  class="p-3" >:</td>
                            <td  class="p-3" ><%= app.getSqlDate() %></td> 
                        </tr>
                        <tr>
                            <th  class="p-3"  scope="row"> Starting Time</th>
                            <td  class="p-3" >:</td>
                            <td  class="p-3" ><%=app.getStartingTime() %></td>
                          </tr>
                          <tr>
                            <th  class="p-3"  scope="row">Ending time</th>
                            <td  class="p-3" >:</td>
                            <td  class="p-3" ><%=app.getEndingTime() %></td>
                          </tr>
                          <tr>
                              <th  class="p-3"  scope="row">Status</th>
                              <td  class="p-3" >:</td>
                              <td  class="p-3" ><%=app.getStatus() %></td> 
                          </tr>
                          <tr>
                            <th  class="p-3"  scope="row">Charges</th>
                            <td  class="p-3" >:</td>
                            <td  class="p-3" > 300 </td>
                        </tr>
                      </tbody>
                </table>
                <%
                	
            			out.println("<div class=\"row\">");
            				out.println("<div class=\"col-6\">");
            					out.println("<a href=\""+app.getDoctor().getRoom()+"\" target=\"_blank\" class=\"btn btn-outline-primary w-50\">Join Room</a>");
            				out.println("</div>");
            				out.println("<div class=\"col-6\">");
            				Coordinator c=(Coordinator)session.getAttribute("userObj");
            				if(c.getId()==1)
            				{
            					out.println("<a href=\"AdminDashboard.jsp\" ><input type=\"button\" value=\"Close\" class=\"btn btn-danger w-50\" ></a>");
            				}
            				else
            				{
            					out.println("<a href=\"CoordinatorDashboard.jsp\" ><input type=\"button\" value=\"Close\" class=\"btn btn-danger w-50\" ></a>");	
            				}
            				out.println("</div>");            
            			out.println("</div>");
            			
            	%>
            </div>
        </div>
    </div>
    
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>