<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/favicon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Other CSS Files -->
  <link href="assets/vendor/fontawesome-free/css/all.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

    <title>Forgot Password</title>

    <style>
        #changePassword{
            display: none;
        }
        #afterEmailSend{
            display: none;
        }
    </style>
  </head>
  <body>
  
  <jsp:include page="Header.jsp"></jsp:include>
    
   
  
  <br><br><br>
    
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-md-6 py-5 section-title">
                <h2 class="text-center"> Forget Password </h2>


                <form action="ForgetPassword" id="forgotPassword" class="forgotPassword formSelect m-5" method="POST">
                    <div class="row g-2 mb-3">
                        <div class="col-md-8">
                            <div class="form-floating">
                                <input type="email" id="searchEmail" class="form-control" name="email" id="floatingInput" placeholder="Email">
                                <label for="floatingInput">Enter Email</label>
                            </div>
                        </div>
                        <div class="col-md">
                           <input type="submit" value="Search" class="btn btn-primary h-100 w-100">
                        </div>
                    </div>
                </form>		

            </div>
        </div>
    </div>
    
  </body>
</html>