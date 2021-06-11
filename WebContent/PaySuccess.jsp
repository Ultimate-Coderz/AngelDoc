<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/favicon.png" rel="apple-touch-icon">

<style type="text/css">
 
    body
    {
        background:#f2f2f2;
    }

 

    /* #main{
         transform: translateY(-1000px);
        animation: ease-in-out 5s ; 
    } */
 
    .payment
    {
        border:1px solid #f2f2f2;
        border-radius:20px;
        background:#fff;
        box-shadow:  26px 26px 38px #acacac,
             -26px -26px 38px #ffffff;
    }
   .payment_header
   {
       background:rgba(0,85,255,1);
       padding:20px;
       border-radius:20px 20px 0px 0px;
       
   }
   
   .check
   {
       margin:0px auto;
       width:50px;
       height:50px;
       border-radius:100%;
       background:#fff;
       text-align:center;
   }
   
   .check i
   {
       vertical-align:middle;
       line-height:50px;
       font-size:30px;
   }
 
    .content 
    {
        text-align:center;
    }
 
    .content  h1
    {
        font-size:25px;
    }
 
    .content a
    {
        width:200px;
        height:35px;
        color:#fff;
        border-radius:30px;
        padding:5px 10px;
        background:rgba(0,85,255,1);
        transition:all ease-in-out 0.3s;
    }
 
    .content a:hover
    {
        text-decoration:none;
        background:rgb(71, 69, 155);
    }
   
</style>
    <title>Payment Success</title>
</head>

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

<body onload="load()">
    <div class="container" id="main">
   <div class="row">
      <div class="col-md-6 mx-auto mt-5">
         <div class="payment">
            <div class="payment_header">
               <div class="check"><i class="fa fa-check" aria-hidden="true"></i></div>
            </div>
            <div class="content py-4 px-5">
               <h1>Payment Success !</h1>
               <p>Your Appointment Request has sent successfully.</p>
               <div class="alert alert-info" role="alert">
                    If the request get decline the amount will rollback to your account.
                </div>
               <a href="PatientDashboard.jsp" class="px-3 py-2">Go to Home</a>
            </div>
            
         </div>
      </div>
   </div>
</div>

 

<script>
    function load(){
        
        setTimeout(function(){ 
            document.getElementById("main").style.transform=" translateY(0)";
         }, 2000);
    }
    
</script>
</body>
</html>