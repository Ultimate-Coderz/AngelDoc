<%@page import="Hibernate.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="Login.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css" />

	<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">

<title>Sign in & Sign up Form</title>
</head>
<body>

	<%
	if (request.getSession().getAttribute("userObj") != null) {
		HttpSession s = request.getSession();
		if (s.getAttribute("userObj") instanceof Doctor) {
			response.sendRedirect("DoctorDashboard.jsp");
		} else if (s.getAttribute("userObj") instanceof Patient) {
			response.sendRedirect("PatientDashboard.jsp");
		} else if (s.getAttribute("userObj") instanceof Coordinator) {
			Coordinator c = (Coordinator) s.getAttribute("userObj");
			if (c.getId() == 1) {
		response.sendRedirect("AdminDashboard.jsp");
			} else {
		response.sendRedirect("CoordinatorDashboard.jsp");
			}
		}
	}
	%>

	<div class="container">
		<div class="forms-container">
			<div class="signin-signup">
				<form action="SignInServ" class="sign-in-form" method="post">
					<h2 class="title">Sign in</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="email"
							placeholder="Username" name="email" id="username" required autofocus />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password" required
							placeholder="Password" id="password1" name="pass" /> <span
							class="show-pass" id="show-pass" onclick="toggle1()"> <i
							class="far fa-eye " id="eye1" onclick="showHide1()"></i>
						</span>
					</div>
					<input type="submit" value="Login" class="btn solid" /> <a
						href="ForgetPassword.jsp"
						style="color: #4d84e2; font-size: 10; padding-top: 20px;"> <b>Forget Password?</b>
					</a>
				</form>
				<form action="SignUpServ" class="sign-up-form" method="post">
					<h2 class="title">Sign Up</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text" placeholder="Name" required 
							id="name" name="uname" />
					</div>
					<div class="input-field">
						<i class="fas fa-envelope"></i> <input type="email" required
							placeholder="Email" id="email" name="email" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							required placeholder="Password" id="password2" name="pass" /> <span
							class="show-pass" id="show-pass" onclick="toggle2()"> <i
							class="far fa-eye " id="eye2" onclick="showHide2()"></i>
						</span>
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Re-type Password" id="repassword" name="repassword"
							onblur="checkPass()" /> <span class="show-pass" id="show-pass"
							onclick="toggle3()"> <i class="far fa-eye " id="eye3"
							onclick="reShowHide()"></i>
						</span>
					</div>
					<input type="submit" id="SignUpBtn" class="btn" disabled value="Sign up" />
				</form>
			</div>
		</div>

		<div class="panels-container">
			<div class="panel left-panel">
				<div class="content">
					<h3>New here ?</h3>
					<p>
						Get started now! Enter your complete and full details such as at
						least 8 digits in password to sign up. <br> Your email should
						be new to our server.
					</p>
					<button class="btn transparent" id="sign-up-btn">Sign up</button>
				</div>
				<img src="img/log.svg" class="image" alt="" />
			</div>
			<div class="panel right-panel">
				<div class="content">
					<h3>One of us ?</h3>
					<p>If you are already our user, login with your email and
						password and get started with your pending work!!</p>
					<button class="btn transparent" id="sign-in-btn">Sign in</button>
				</div>
				<img src="img/register.svg" class="image" alt="" />
			</div>
		</div>
	</div>

	<script src="assets/js/app.js"></script>
	 <script src="assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js"></script>
  <script src="assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js"></script>
  <script src="https://kit.fontawesome.com/64d58efce2.js" crossorigin="anonymous"></script>
	<script type="text/javascript">
		function checkPass()
		{
			let password = document.querySelector("#password2").value;
			  let repassword = document.querySelector("#repassword").value;
			  var chk = (password==repassword);
			  if(!chk){
				  
				  Notiflix.Notify.Init({fontSize:"16px",useFontAwesome:true,position:"right-bottom"});
				  Notiflix.Notify.Failure("Password didn't match");  
			  }
			  else
				  {
				  		document.getElementById("SignUpBtn").removeAttribute("disabled");
				  }
		}
	</script>
</body>
</html>
