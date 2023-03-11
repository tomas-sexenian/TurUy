<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!-- LOGIN: mostrar solo si no esta loggeado -->

<c:set var = "msg" scope="session" value="${alert}" />

<c:if test="${not empty msg }">

 	<script type="text/javascript">
 		var msg = '${alert}';
 		function alertName(){
 			alert(msg);
 		}
 		window.onload = alertName;
 	</script> 
</c:if>

<c:set var = "user" scope="session" value="${sessionScope.usuario_logueado}" />

<c:if test="${empty user}" >

<div class="container-fluid  pt-1 d-lg-block d-md-block d-sm-block align-content-end" >
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 text-right" style="padding-top: 20px; padding-bottom: 10px">
            <div class=" d-inline-flex" data-toggle="dropdown" >
                <a  href="#" style="color:  #343a40; font-size: medium" >Iniciar sesión <i class="fa fa-sign-in-alt" ></i></a>
                <div class="dropdown-menu" style="max-height: none" >
                    <div class="login-form">
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
                        <div class="text-center small">¿No esta registrado? <a href="altaUsuario">Crear Usuario</a></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</c:if>

<c:if test="${not empty user}" >


<!-- LOGOUT: mostrar si hay usuario loggeado loggeado-->
<div class="container-fluid  pt-1 d-lg-block d-md-block d-sm-block align-content-end" >
    <div class="row" >
        <div class="col-lg-12 col-md-12 col-sm-12 text-right py-1">
            <div class=" d-inline-flex " data-toggle="dropdown">
            
				<c:set var = "img" scope="session" value="${sessionScope.foto_usuario}" />
				<c:if test="${empty img }">
					<c:set var = "img" scope="session" value="${initParam.defaultImgUser}" />
				</c:if>
                <img class="profile-img" src="Imagenes?id=${img}">
                <div class="dropdown-menu border-0 m-0 text-right">
                    <a class="dropdown-item" type="submit" href="logout">Cerrar Sesión <i class="fa fa-sign-out-alt"></i></a>
                </div>
            </div>
            <div class="row pt-0 d-none d-lg-block" id="name">
                <span style="display: block"><c:out value="${sessionScope.nombre_usuario}" /></span>
            </div>
        </div>
    </div>
</div>

</c:if>

<!-- Topbar Start -->
<div class="container-fluid bg-light pt-3 d-none d-lg-block">
    <div class="container">
        <div class="row" >
            <div class="col-lg-6 text-center text-lg-left mb-2 mb-lg-0">
                <div class="d-inline-flex align-items-center" >
                    <p><i class="fa fa-envelope mr-2"></i>info@turismouy.com</p>
                    <p class="text-body px-3">|</p>
                    <p><i class="fa fa-phone-alt mr-2"></i>1-603-413-4124</p>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid position-relative nav-bar p-0">
    <div class="container-lg position-relative p-0 px-lg-3" style="z-index: 9;">
        <nav class="navbar navbar-expand-lg bg-light navbar-light shadow-lg py-3 py-lg-0 pl-3 pl-lg-5" >
            
            <h1 class="m-0 text-primary">
                <a href="home" class="navbar-brand text-primary"  style="font-size: inherit"><span class="text-dark">TURISMO</span>UY</a>
            </h1>
            
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between px-3" id="navbarCollapse">
                <div class="navbar-nav ml-auto py-0">

                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Departamentos</a>
                        <div class="dropdown-menu border-0 rounded-0 m-0">   
                        	<c:forEach items="${applicationScope.departamentos}" var="depto" varStatus="loop">
                            	<a href="actividades?departamento=${depto}" class="dropdown-item">${depto}</a>
                            </c:forEach>                      
                        </div>


                    </div>

                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Categorias</a>
                        <div class="dropdown-menu border-0 rounded-0 m-0">
                        	<c:forEach items="${applicationScope.categorias}" var="cat" varStatus="loop">
                            	<a href="actividades?categorias=${cat}" class="dropdown-item">${cat}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->


<script src="js/jquery-3.6.1.min.js"></script>

<script src="js/navbar.js"></script>

</body>
</html>
