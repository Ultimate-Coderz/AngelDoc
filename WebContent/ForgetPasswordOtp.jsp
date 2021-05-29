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

  </head>
  <body onload="sendEmail()">
  
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
                                <input type="email" id="searchEmail" class="form-control" disabled value="<%= request.getAttribute("email") %>" name="email" id="floatingInput" placeholder="Email">
                                <label for="floatingInput">Enter Email</label>
                            </div>
                        </div>
                        <div class="col-md">
                           <input type="submit" onclick="" disabled value="Search" class="btn btn-primary h-100 w-100">
                        </div>
                    </div>
                </form>
                <%
                	
                	if(request.getAttribute("correct")==null)
                	{
                		response.sendRedirect("Login.jsp");
                	}
                
                	if( (Boolean) request.getAttribute("correct") )
                	{                		
                %>
		          		<div class="alert alert-danger " role="alert">
		                    No User Found
		                </div>
				<%
					}
                	else
					{
				%> 

					
				<div id="afterEmailSend">
                    <div class="alert alert-success" role="alert">
                        Verification code has been sent to your mail.
                    </div>
    
                    <div class="row g-2 mb-3">
	                    <div class="form-floating col-md-8">
	                        <input type="text" class="form-control" id="otp" placeholder="otp" >
	                        <label for="floatingInput">Enter OTP</label>
	                    </div>
	                     <div class="col-md">
	                        <input type="button" onclick="showChangePass()" value="Validate OTP" class="btn btn-primary h-100 w-100">
	                    </div>
					</div>
                </div>
				<%	
					}
				%>
            </div>
        </div>
    </div>


<!--OTP Modal -->
<div class="modal fade" id="otpModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="color: #1977cc;">
        <h5 class="modal-title" id="exampleModalLabel" style=" font-weight: bolder; "><img src="assets/img/logo.JPG" class="image-responsive" height="50px" alt="" srcset=""></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="color: white;"></button>
      </div>
      <div class="modal-body" id="modal-body text-center">
        <form id="changePassword" class="changePassword formSelect m-5 " action="ResetPassword" method="post" >
          <div class="form-floating mb-3">
            <input type="password" class="form-control" id="password2" name="pass" placeholder="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" >
            <label for="password2">Enter New Password</label>
          </div>
    	  <div class="form-floating mb-3">    
      	  	<input type="password" class="form-control" id="repassword" name="repass" placeholder="password" onblur="check()">
          	<label for="repassword">Confirm Password</label>
          </div>
          <input type="hidden" id="email" name="email"  value="<%= request.getAttribute("email") %>">
          <input type="submit" value="Submit" class="btn btn-primary">
      </form> 
      </div>
    </div>
  </div>
</div>

    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2/dist/email.min.js"></script>
    <script type="text/javascript">
    (function() {
    emailjs.init("user_T94DNlsgMxBgcG3jhzPLq");
    })();
    </script>
    <script type="text/javascript">
        var code= <%=request.getAttribute("otp") %>; 
        function sendEmail(params)
        {
            var email =document.getElementById("searchEmail").value;
            emailjs.send( "service_dskoa21","template_6hf48gm", {
            toemail: email,
            code: code,
            })
            .then(function(res){
                console.log("Success" , res.status);
            });
            document.getElementById("afterEmailSend").style.display="block";
        }
        
        function showChangePass(){
        	var otp = parseInt( document.getElementById('otp').value);
	    	 if(otp==code){
	    	 var otpModal = new bootstrap.Modal(document.getElementById('otpModal'), {
	    	      keyboard: false
	    	});
	    	otpModal.show();
   	  }
        }
    </script>

    <script src="./assets/js/app.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <script src="./assets/vendor/bootstrap/js/bootstrap.bundle.js"></script>
  </body>
</html>
