const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

// For Sliding Animations
sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
  document.getElementById("eye").classList.remove("fa-eye-slash");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
  document.getElementById("eye").classList.remove("fa-eye-slash");
});

function check(){
  let password = document.querySelector("#password2").value;
  let repassword = document.querySelector("#repassword").value;
  var chk = (password==repassword);
  if(!chk){
    alert("Password didn't match.")
  }
}

function reShowHide() {
  var repass = document.getElementById("repassword");
  if (repass.type == "password") {
  repass.type = "text";
  } else {
    repass.type = "password";
  }
}

function showHide1() {
  var pass = document.getElementById("password1");
  if (pass.type == "password") {
  pass.type = "text";
  } else {
    pass.type = "password";
  }
}
function showHide2() {
  var pass = document.getElementById("password2");
  if (pass.type == "password") {
  pass.type = "text";
  } else {
    pass.type = "password";
  }
}

function toggle1() {
  var eye = document.getElementById("eye1");
  eye.classList.toggle("fa-eye-slash")
}
function toggle2() {
  var eye = document.getElementById("eye2");
  eye.classList.toggle("fa-eye-slash")
}
function toggle3() {
  var eye = document.getElementById("eye3");
  eye.classList.toggle("fa-eye-slash")
}