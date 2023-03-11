<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>TURISMO.UY</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="img/favicon.png" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">


    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>

<!-- Include navbar -->
<%@ include file="navbar.jsp"%>

<c:set var="UPLOAD_DIRECTORY"  value="${initParam.filedir}"/>

    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Usuarios</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Usuarios</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->

<!-- Buscador Start -->

<c:set var = "tipoUsuarioSelected" scope="request" value="${requestScope.tipo}" />

<div class="container-fluid booking mt-5 pb-5">
        <div class="container pb-5">
            <div class="bg-light shadow" style="padding: 30px;">
                <div class="row align-items-center" style="min-height: 60px;">
                    <div class="col-md-10">
                        <form action="consultaUsuario" id ="usrs">
                        <div class="row">
                        
                            <div class="col-md-12">
                                <div class="mb-3 mb-md-0">
                                    <select name="tipo" id="tipo" class="custom-select px-4" style="height: 47px;">
                                    	
                                    	<option <c:if test="${tipoUsuarioSelected == 'Turistas y Proveedores'}"> selected</c:if> <c:if test="${empty tipoUsuarioSelected}"> selected</c:if> value="Turistas y Proveedores" selected >Turistas y Proveedores</option>
                                   		<option <c:if test="${tipoUsuarioSelected == 'Proveedores'}"> selected</c:if> value='Proveedores'>Proveedores</option>
                                        <option <c:if test="${tipoUsuarioSelected == 'Turistas'}"> selected</c:if>  value='Turistas'>Turistas</option>
                                    
                                    </select>
                                </div>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary btn-block" form="usrs" type="submit" style="height: 47px; margin-top: -2px;">Buscar</button>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- Buscador End -->


    <!-- Usuarios Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="text-center mb-3 pb-3">
                <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Usuarios</h6>
             <c:if test="${empty tipoUsuarioSelected}">
          		<c:set var = "tipoUsuarioSelected" scope="request" value="Turistas y Proveedores" />
          	</c:if>
                <h1>Nuestros ${tipoUsuarioSelected}</h1>
            </div>
            <div class="row">
           		<c:forEach items="${requestScope.turistas}" var="turista" varStatus="loop">
	                <div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
	                <c:set var = "imgU" scope="session" value="${turista.imgSrc}" />
					<c:if test="${empty imgU }">
						<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
					</c:if>
	                    <img class="img-fluid mx-auto" src="${UPLOAD_DIRECTORY}/${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                    <c:set var = "imgU" scope="session" value="${requestScope.turista.imgSrc}" />
					<c:if test="${empty imgU }">
						<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
					</c:if>
	                    <div class="testimonial-text bg-white p-4 mt-n5">
	                        <p class="mt-5"></p>
	                        <a class="h5 text-decoration-none" href="consultaUsuario?usuario=${turista.nickname}">${turista.nombre} ${turista.apellido}</a><br>
	                        <span>Turista</span>
	                    </div>
	                </div>
	        	</c:forEach>
	        	
			<c:forEach items="${requestScope.proveedores}" var="proveedor" varStatus="loop">
	            <div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
		            <c:set var = "imgU" scope="session" value="${proveedor.imgSrc}" />
					<c:if test="${empty imgU }">
						<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
					</c:if>
	                    <img class="img-fluid mx-auto" src="${UPLOAD_DIRECTORY}/${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                    <div class="testimonial-text bg-white p-4 mt-n5">
	                        <p class="mt-5"></p>
	                        <a class="h5 text-decoration-none" href="consultaUsuario?usuario=${proveedor.nickname}">${proveedor.nombre} ${proveedor.apellido}</a><br>
	                        <span>Proveedor</span>
	                    </div>
	                </div>
	        </c:forEach>
<!--             <div class="text-center pb-4 col-lg-4 col-md-6 mb-4"> -->
<!--                     <img class="img-fluid mx-auto" src="img/pablo.jpeg" style="width: 100px; height: 100px; object-fit: cover;" > -->
<!--                     <div class="testimonial-text bg-white p-4 mt-n5"> -->
<!--                         <p class="mt-5"></p> -->
<!--                         <a class="h5 text-decoration-none" href="consultaProveedor.html">Pablo Bengoechea</a><br> -->
<!--                         <span>Proveedor</span> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             <div class="text-center pb-4 col-lg-4 col-md-6 mb-4"> -->
<!--                     <img class="img-fluid mx-auto" src="img/mercedes.jpeg" style="width: 100px; height: 100px; object-fit: cover;" > -->
<!--                     <div class="testimonial-text bg-white p-4 mt-n5"> -->
<!--                         <p class="mt-5"></p> -->
<!--                         <a class="h5 text-decoration-none" href="consultaProveedor.html">Mercedes Venn</a><br> -->
<!--                         <span>Proveedor</span> -->
<!--                     </div> -->
<!--                 </div> -->
            </div>
        </div>
    </div>
    <!-- Usuarios End -->

<!-- Include Footer -->

<%@ include file="footer.jsp"%>

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>
