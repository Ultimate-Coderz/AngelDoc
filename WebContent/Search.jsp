
<%@page import="dao.SearchDAO, java.util.*, Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Search</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
	
	<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">
	
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
</head>

<body>

	
  <jsp:include page="Header.jsp"></jsp:include>
  
  <%
     	 SearchDAO sdao = new SearchDAO();
       	 List<String> list = sdao.getDepartments();
       	List<String> listCity = sdao.getCities();
   %>
	<br><br>
	<div class="container ">
		<div class="row justify-content-md-center">
			<div class="col-md-6 py-5 section-title">
				<h2 class="text-center ">Search</h2>
			</div>
			<form action="NewSearchServ" method="POST">
				<div class="row">

					<div class="col-md-5">
						<div class="form-floating mb-3">
							<input type="search" class="form-control" name="name"
								id="floatingInput" placeholder="Search here"> <label
								for="floatingInput">Search<i class="fa-fa-"></i></label>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-floating mb-3 ">
							<select class="form-select py-3 " name="spec" id="">
								<option selected>Specialization</option>
                                <%for(String s: list){ %>
                                <option><%= s %></option>
                                <% } %>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-floating mb-3">
							<select class="form-select py-3 " name="city" id="">
								<option selected>City</option>
                                <%for(String s: listCity){ %>
                                <option><%= s %></option>
                                <% } %>
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="form-floating mb-3 mt-1">
							<input type="submit" value="Search"
								class="btn btn-outline-primary btn-lg py-2 px-6">
						</div>
					</div>

				</div>
			</form>
			<div class="col-md-12 py-4">
				<table class="table table-responsive table-sm table-hover">
					<thead>
						<tr>
							<th class='text-center' scope="col">ID</th>
							<th class='text-center' scope="col">NAME</th>
							<th class='text-center' scope="col">SPECIALIZATION</th>
							<th class='text-center' scope="col">WORKING HOURS</th>
							<th class='text-center' scope="col">CITY</th>
							<th class='text-center' scope="col">CHARGES</th>
							<th class='text-center' scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Doctor> docList = null;
						String name = request.getParameter("name");

						if (name == null) 
						{
							docList = SearchDAO.listAllDoctors();
							if (docList.size() == 0) {
								out.println("<tr><th colspan=7 rowspan=2 style='color:red;' scope='row' class='text-center'>"
										+ "No data found" + "</th></tr>");
									}
							for (int i = 0; i < docList.size(); i++) {
								Doctor d = docList.get(i);
								out.println("<tr>" + "<th scope='row' class='text-center'>" + (i+1) + "</th>" + "<td class='text-center'>"
								+ d.getFirstName() + " " + d.getLastName() + "</td>" + "<td class='text-center'>" + d.getDocSpec()
								+ "</td>" + "<td class='text-center'>" + d.getStartingHr() + " - " + d.getEndingHr() + "</td>"
								+ "<td class='text-center'>" + d.getCity() + "</td>" + "<td class='text-center'>300</td>"
								+ "<td class='text-center'><a href='AppointServ?id=" + d.getDocId()
								+ "'class='btn btn-outline-primary'>Book an Appointment</a></td>" + "</tr>");
							}
						} 
						else 
						{
							if (request.getAttribute("list") != null) {
								docList = (List<Doctor>) request.getAttribute("list");
								if (docList.size() == 0) {
							out.println("<tr><th colspan=7 rowspan=2 style='color:red;' scope='row' class='text-center'>"
									+ "No data found" + "</th>");
								}
								for (int i = 0; i < docList.size(); i++) {
							Doctor d = docList.get(i);
							out.println("<tr>" + "<th scope='row' class='text-center'>" + (i+1) + "</th>" + "<td class='text-center'>"
									+ d.getFirstName() + " " + d.getLastName() + "</td>" + "<td class='text-center'>" + d.getDocSpec()
									+ "</td>" + "<td class='text-center'>" + d.getStartingHr() + " - " + d.getEndingHr() + "</td>"
									+ "<td class='text-center'>" + d.getCity() + "</td>" + "<td class='text-center'>300</td>"
									+ "<td class='text-center'><a href='AppointServ?id=" + d.getDocId()
									+ "'class='btn btn-outline-primary'>Book an Appointment</a></td>" + "</tr>");
								}

							} else {
								docList = SearchDAO.listAllDoctors();
								for (int i = 0; i < docList.size(); i++) {
									Doctor d = docList.get(i);
									out.println("<tr>" + "<th scope='row' class='text-center'>" + (i+1) + "</th>" + "<td class='text-center'>"
									+ d.getFirstName() + " " + d.getLastName() + "</td>" + "<td class='text-center'>" + d.getDocSpec()
									+ "</td>" + "<td class='text-center'>" + d.getStartingHr() + " - " + d.getEndingHr() + "</td>"
									+ "<td class='text-center'>" + d.getCity() + "</td>" + "<td class='text-center'>300</td>"
									+ "<td class='text-center'><a href='AppointServ?id=" + d.getDocId()
									+ "'class='btn btn-outline-primary'>Book an Appointment</a></td>" + "</tr>");
								}
							}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>

</html>