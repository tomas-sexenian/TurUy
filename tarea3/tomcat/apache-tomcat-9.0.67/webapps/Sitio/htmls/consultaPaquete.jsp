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
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">


    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
	<link href="css/pestanas.css" rel="stylesheet">
	
	

</head>

<body>

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


<!-- Include navbar -->
<%@ include file="navbar.jsp"%>

<!-- Header Start -->
<div class="container-fluid page-header">
    <div class="container">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
            <h3 class="display-4 text-white text-uppercase">Consulta de Paquete</h3>
            <div class="d-inline-flex text-white">
                <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase"><a class="text-white" href="paquetes">Paquetes</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase">Consulta de Paquete</p>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- Consulta Start -->
<c:set var="from" value="htmls/consultaPaquete.jsp" scope="session"  />

<div class="container-fluid p-0">

	<div class="container-fluid py-5">
		<div class="container pt-5">
			<div class="row">
				<div class="col-lg-6" style="min-height: 500px;">
					<div class="position-relative h-100">
						<c:set var = "img" scope="session" value="${sessionScope.paquete.imgSrc}" />
						<c:if test="${empty img }">
							<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
						</c:if>
						<img class="position-absolute w-100 h-100" src="Imagenes?id=${img}" style="object-fit: cover;" alt="">
					</div>
				</div>
				<div class="col-lg-6 pt-5 pb-lg-5">
					<div class="about-text bg-white p-4 p-lg-5 my-lg-5">
						<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Paquetes</h6>
						<h1 class="mb-3">${sessionScope.paquete.nombre}</h1>
						<div class="p-4">
							<!-- 
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2" style="font-size: large"></i>Rocha, Rocha</div>
							</div>  -->
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-calendar-alt text-primary mr-2" style="font-size: large"></i>${sessionScope.paquete.validez} dias</div>
							</div>
							<div class="d-flex justify-content-between">
								<h5 class="m-0">Descuento del paquete: ${sessionScope.paquete.descuento}%</h5>
							</div>
							<div class="d-flex justify-content-between" style="padding-bottom: 20px; padding-top: 20px">
								<div class="m-0"><i class="fa fa-usd text-primary mr-2" style="font-size: large" aria-hidden="true"></i>Precio del paquete por persona: ${sessionScope.costo}</div>
							</div>
							
							<div class="d-flex justify-content-between" style="padding-bottom: 20px; padding-top: 20px">
								<div class="m-0"><i class="fa fa-newspaper-o text-primary mr-2" style="font-size: large" aria-hidden="true"></i> ${sessionScope.paquete.descripcion}</div>
							</div>
							
							
							
							<!-- solo se compra paquete si tiene actividades y el usuario loggeado es turista -->
							<c:set var = "activ" scope="session" value="${sessionScope.actividadesPaq}" />
							
							<c:if test="${not empty activ}">
								
								<c:set var = "user" scope="session" value="${sessionScope.usuario_logueado}" />
								<c:set var = "tipo" scope="session" value="${sessionScope.tipo_usuario}" />							
								<c:choose>
									<c:when test="${empty user}" >
										<div class="d-flex justify-content-between pt-3">
	                        				<div class="m-0"><a href="altaUsuario">Registrarse</a> o <a href="Login">iniciar sesion</a> para comprar un paquete</div>
	                    				</div>
									</c:when>
									
									<c:when test="${tipo.equals('Proveedor')}" >
										<div class="d-flex justify-content-between mb-3">
											<div class="m-0"><i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: large"></i>Para comprar un paquete es necesario iniciar sesión como turista</div>
										</div>	
									</c:when>
									<c:when test="${tipo.equals('Turista')}" >
										<button class="btn btn-primary btn-block" data-toggle="dropdown"  style="height: 47px">Comprar paquete</button>
										<div class="col-lg-8 dropdown-menu dropdown-menu-high" >
											<div class="card border-0">
												<div class="card-body rounded-bottom bg-white p-5">
													<form action="compraPaquete" method="post">
														<div class="form-group">
															<input type="number" name="cantidadTuristas" class="form-control px-4" id="cTuristas"  min="1" style="height: 47px" placeholder="Cantidad de turistas" data-target="#costo" required="required"/>
														</div>
														<div>
															<input type="hidden" name="nombrePaquete" value="${sessionScope.paquete.nombre}" />
														</div>
														<div>
                            								<input type="submit" id="log" class="btn btn-primary btn-block py-2" value="Confirmar Compra">
														</div>
													</form>
												</div>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:if>
							<div class="border-top mt-4 pt-4"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container py-4 b">

		<div class="row">
			<div class="col-md-3">
				<!-- Tabs nav -->
				<div class="nav flex-column nav-pills nav-pills-custom" id="v-pills-tab" role="tablist" aria-orientation="vertical">
					<a class="nav-link mb-3 p-3 shadow active" id="v-pills-actividades-tab" data-toggle="pill" href="#v-pills-actividades" role="tab" aria-controls="v-pills-actividades" aria-selected="true">
						<i class="fa fa-user-circle-o mr-2"></i>
						<span class="font-weight-bold small text-uppercase">Actividades</span></a>

					<a class="nav-link mb-3 p-3 shadow" id="v-pills-categorias-tab" data-toggle="pill" href="#v-pills-categorias" role="tab" aria-controls="v-pills-categorias" aria-selected="false">
						<i class="fa fa-star mr-2"></i>
						<span class="font-weight-bold small text-uppercase">Categorias</span></a>

				</div>
			</div>


			<div class="col-md-9">
				<!-- Tabs content -->
				<div class="tab-content" id="v-pills-tabContent">
					<div class="tab-pane fade shadow rounded bg-white show active p-5" id="v-pills-actividades" role="tabpanel" aria-labelledby="v-pills-actividades-tab">

						<div class="text-center mb-3 pb-3">
							<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Paquetes</h6>
							<h2>Actividades Incluidas en el Paquete</h2>
						</div>
						<div class="row">
							<c:forEach items="${sessionScope.actividadesPaq}" var="act" varStatus="loop">
							<div class="col-lg-6 col-md-6 mb-4">
								<div class="package-item bg-white mb-2">
									<c:set var = "img" scope="session" value="${act.imageSrc}" />
									
									<c:if test="${empty img }">
										<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
									</c:if>
									<img class="img-fluid" src="${initParam.filedir}/${img}" alt="" style="height: 180px; margin: 5%; width: 90%; object-fit: cover;">
									<div class="p-4">
										<div class="d-flex justify-content-between mb-3">
											<small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${act.departamento.nombre}</small>
											<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${act.duracion} dias</small>
										</div>
										<a class="h5 text-decoration-none" href="consultaActividad?actividad=${act.nombre}">${act.nombre}</a>
										<div class="border-top mt-4 pt-4">
											<div class="d-flex justify-content-between"></div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>



					<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-categorias" role="tabpanel" aria-labelledby="v-pills-categorias-tab">
						<div class="text-center mb-3 pb-3">
							<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Categorias</h6>
							<h2>Categorias asociadas al Paquete</h2>
						</div>
						<c:forEach items="${sessionScope.categoriasPaq}" var="cat" varStatus="loop">
							<p>
								<a href="actividades?categoria=${cat}">${cat} </a>
							</p>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>


<!-- Include Footer -->
<%@ include file="footer.jsp"%>


    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="js/navbar.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>