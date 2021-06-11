<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Hibernate.*"%> 
    <%@ page import="dao.*"%> 
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
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">

 

  <!-- Other CSS Files -->
  <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">

 

  <!-- Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

 

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

 

  <title>Payment</title>

 

  <style>
    .hideForm{
      display: none;
    }
  </style>

 

</head>
<body style="background: #f4f5f7d5;">
	
	

  <div class="" style="background-color: #0099ff;">
    <div class="row justify-content-center">
      <div class="col-md-10">
        <h1 class="text-center py-3 text-light" style="font-family: 'Roboto', sans-serif;"> <b>AngleDoc<sup>+</sup></b> Payment </h1>
      </div>
    </div>
  </div>
  <div class="container my-3">
    <div class="row justify-content-center" >
      <div class="col-md-7">
        <div class="content border">
          <div class="row m-2">
            <div class="alert alert-info" role="alert">
              Payable Amount : Rs. 300
            </div>
            <%
	    		if (request.getSession(false) == null) 
	    		{
	    			response.sendRedirect("Login.jsp");
	    		}
	    		if (session.getAttribute("userObj") == null) {
	    			response.sendRedirect("Login.jsp");
	    		}
	    		
	            Doctor doc = (Doctor)session.getAttribute("docObj");
	            String docName = doc.getFirstName()+" "+doc.getLastName();
	            Patient pat = (Patient)session.getAttribute("userObj");
	            String patName = pat.getFirstName()+" "+pat.getLastName();
	            
	            String appointmentStartingTime =(String) request.getAttribute("startingTime");
	            String dateString=(String)request.getParameter("date");
	            
	            request.setAttribute("startingTime", appointmentStartingTime);
	            request.setAttribute("date", dateString);
            
            %>
        
            <div class="alert alert-primary" role="alert">
              Doctor Name :    <%= docName %> <br><br>
              Patient Name : <%= patName %>
            </div>

 

            <div class="row p-0 m-0">
              <div class="col-4">
                <button class="btn btn-outline-primary w-100 h-100" onclick="hideShow('payCard')">
                  Use Card
                </button>
              </div>
              <div class="col-4">
                <button class="btn btn-outline-primary w-100 h-100" onclick="hideShow('payUPI')">
                  Use UPI
                </button>
              </div>
              <div class="col-4">
                <a class="btn btn-outline-primary w-100 h-100" onclick="hideShow('payQR')">
                  QR Code
                </a>
              </div>
            </div>

 

            <form id="payCard" class="border p-2 my-2 hideForm" action="BookAppointmentServ" method="post">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="cardNo" placeholder="name@example.com">
                    <label for="floatingInput">Card Number</label>
                  </div>

 

                  <div class="row g-2 mb-3">
                    <div class="col-md">
                      <div class="form-floating">
                        <input type="text" class="form-control" id="expDate" placeholder="mm/yy" >
                        <label for="floatingInputGrid"> Expiry Date</label>
                      </div>
                    </div>
                    <div class="col-md">
                      <div class="form-floating">
                        <input type="text" class="form-control" id="cvv" size="3" placeholder="XXX">
                        <label for="floatingInputGrid"> CVV</label>
                      </div>
                    </div>
                  </div>

 

                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="cname" placeholder="name@example.com">
                    <label for="floatingInput">Card Holder Name</label>
                  </div>
                    <input type="hidden" value=<%= appointmentStartingTime %> name="startingTime">                            
                    <input type="hidden" value=<%= dateString %> name="date">
                  <input type="button" value="Pay" class="btn btn-primary w-100" onclick="submitcard()">

 

                  <div id="alert-error-card" style="display: none;">
                    <div class="my-3 alert alert-danger d-flex align-items-center" role="alert">
                      <div>Invalid Details. Try again!</div>
                    </div>
                  </div>
            </form>

 

            <form id="payUPI" class="border p-2 my-2 hideForm" method="post"> 
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="upiId" placeholder="Your UPI ID">
                    <label for="floatingInput">UPI ID</label>
                  </div>
                    <input type="hidden" value=<%= appointmentStartingTime %> name="startingTime">       
                    <input type="hidden" value=<%= dateString %> name="date">
                  <input type="button" value="Pay" class="btn btn-primary w-100" onclick="payUPI()">

 

                  <div id="alert-error-upi" style="display: none;">
                    <div class="my-3 alert alert-danger d-flex align-items-center" role="alert">
                      <div>Invalid Details. Try again!</div>
                    </div>
                  </div>
            </form>

 

            <form id="payQR" class="border p-2 my-2 hideForm" action="BookAppointmentServ" method="post">
            <input type="hidden" value=<%= appointmentStartingTime %> name="startingTime">     
                    <input type="hidden" value=<%= dateString %> name="date">
              <img class="rounded mx-auto d-block img-fluid" src='img/QR.png' alt='QR Code'/>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

 


 <!--OTP Modal -->
<div class="modal fade" id="otpModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">AngleDoc+</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="payCard" class="p-2" action="BookAppointmentServ" method="post" >
          <div class="form-floating mb-3">
            <input type="text" maxlength="6" size="6" class="form-control" id="otoNo" placeholder="XXXXXX">
            <label for="floatingInput">Enter OTP</label>
          </div>
          <input type="submit" value="Pay" class="btn btn-primary w-100" onclick="chkOTP()" aria-autocomplete="none">
          <div id="alert-error-otp" style="display: none;">
            <div class="my-3 alert alert-danger d-flex align-items-center" role="alert">
              <div>Entered OTP is incorrect.</div>
            </div>
          </div>
          <input type="hidden" value=<%= appointmentStartingTime %> name="startingTime">     
                    <input type="hidden" value=<%= dateString %> name="date">
        </form>
      </div>
    </div>
  </div>
</div>
  <script>
    function hideShow(id){
      document.getElementById("payCard").style.display="none";
      document.getElementById("payUPI").style.display="none";
      document.getElementById("payQR").style.display="none";
      document.getElementById(id).style.display="block";
    }

    function showOtpModel(){
      var otpModal = new bootstrap.Modal(document.getElementById('otpModal'), {
      keyboard: false
      });
      otpModal.show();
    }

    function payUPI(){
      var upiId = document.getElementById("upiId").value;
      if(upiId=="8390945445@okaxis"){
        showOtpModel();
      }else{
        document.getElementById("alert-error-upi").style.display="block";
      }
    }

    function payCard(){
      var cardNo = document.getElementById("cardNo").value;
      var expDate = document.getElementById("expDate").value;
      var cvv = document.getElementById("cvv").value;
      var cname = document.getElementById("cname").value;


      if(cardNo=="7039522236699221" && expDate=="10/22" && cvv=="778" && cname=="Vignesh Gupta"){
        showOtpModel();
      }else{
        document.getElementById("alert-error-card").style.display="block";
      }
    }

    function chkOTP(){
      var otp = parseInt( document.getElementById('otoNo').value);
      if(otp==231002){
        window.location.href ="./PaySuccess.html";
      }else{
        document.getElementById("alert-error-otp").style.display="block";
      }

    }
  </script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
</body>
</html>