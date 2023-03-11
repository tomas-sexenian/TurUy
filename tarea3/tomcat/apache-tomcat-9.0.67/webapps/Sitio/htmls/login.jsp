<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<div class="container" style="position: relative; ">
<div class="login-form" style="position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, 50%);
  transform: translate(-50%, 50%);">
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
    <div class="text-center small">¿No esta registrado? <a href="htmls/altaUsuario.html">Crear Usuario</a></div>
</div>
</div>

</body>
</html>