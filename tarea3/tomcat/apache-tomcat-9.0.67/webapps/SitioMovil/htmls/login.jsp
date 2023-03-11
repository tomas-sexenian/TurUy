<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TURISMO.UY</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.png" rel="icon">

    <!-- Google Web Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">


    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    
</head>
<body>
<body style="background-color:white">

<c:set var = "msg" scope="session" value="${alert}" />

<c:if test="${not empty msg }">
 <script type="text/javascript">
 	function error() {
		let timerInterval
		Swal.fire({
			title: 'Error!',
			html: 'El usuario no existe o la contraseña no es correcta',
			timer: 3000,
			timerProgressBar: true,  
			willClose: () => {
 				clearInterval(timerInterval)
			}
		})
	}
 	window.onload = error;
</script>

</c:if>

<h1 class="m-0 text-primary text-center pt-5">
                <a href="home" class="navbar-brand text-primary"  style="font-size: inherit"><span class="text-dark">TURISMO</span>UY</a>
            </h1>

<div class="login-form" style="height: 100% !important; max-height: 100% !important; padding: 0">
  <form action="Login" method="post" >
    	<div class="avatar"><i class="fas fa-user"></i></div>
        <h4 class="modal-title">Iniciar sesión</h4>
        <div class="form-group">
           	<input type="text" class="form-control" name="username" id="username" placeholder="&#xF007;  Nickname o Email" required="required" style="font-family:Poppins, FontAwesome" />
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" id="password"  placeholder="&#xF023;  Password" required="required" style="font-family:Poppins, FontAwesome" />
        </div>

        <input type="submit" id="log" class="btn btn-primary btn-block btn-lg" value="Login">

		<script>
            var input = document.getElementById("password");
            input.addEventListener("keypress", function(event) {
                if (event.key === "Enter") {
                	event.preventDefault();
                	document.getElementById("log").click();
                }
            });
        </script>
    </form>
</div>

    <!-- Include -->
     <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</body>
</html>