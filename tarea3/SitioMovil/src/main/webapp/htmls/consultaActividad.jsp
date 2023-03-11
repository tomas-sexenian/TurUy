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

    <!-- Include -->
    <script src="js/main.js" defer></script>
</head>

<body>


<!-- Include navbar -->
<%@ include file="navbar.jsp"%>


<!-- Header Start -->
<div class="container-fluid page-header">
	<div class="container">
		<div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
			<h3 class="display-4 text-white text-uppercase text-center">Consulta de Actividad</h3>
			<div class="d-inline-flex text-white">
				<p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
				<i class="fa fa-angle-double-right pt-1 px-3"></i>
				<p class="m-0 text-uppercase">Consulta de Actividad</p>
			</div>
		</div>
	</div>
</div>
<!-- Header End -->

<!-- Consulta Start -->
<c:set var="from" value="htmls/consultaActividad.jsp" scope="session"  />

<div class="container-fluid p-0">

	<div class="container-fluid py-5">
		<div class="container pt-5">
			<div class="row">
				<div class="col-lg-6" style="min-height: 500px;">
					<div class="position-relative h-100">
						<c:set var = "img" scope="session" value="${sessionScope.actividad.imageSrc}" />
						<c:if test="${empty img }">
							<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
						</c:if>
						<img class="position-absolute w-100 h-100" src="Imagenes?id=${img}" style="object-fit: cover;" alt="">
					</div>
				</div>
				<div class="col-lg-6 pt-5 pb-lg-5">
					<div class="about-text bg-white p-4 p-lg-5 my-lg-5">
						<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Actividades</h6>
						<h1 class="mb-3">${sessionScope.actividad.nombre}</h1>
						<div class="p-4">
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2" style="font-size: large"></i>${sessionScope.actividad.departamento.nombre}, ${sessionScope.actividad.ciudad}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-calendar-alt text-primary mr-2" style="font-size: large"></i>${sessionScope.actividad.duracion} horas</div>
							</div>
							<div class="d-flex justify-content-between pb-2">
								<h5 class="m-0">Costo de la actividad: $${sessionScope.actividad.costo}</h5>
							</div>
							<div class="d-flex justify-content-between">
								<div class="m-0"><i class="fa fa-newspaper-o" style="font-size: large" aria-hidden="true"></i> ${sessionScope.actividad.descripcion}</div>
							</div>
							<c:set var = "user" scope="session" value="${sessionScope.usuario_logueado}" />
							<c:set var = "prov" scope="session" value="${sessionScope.proveedor}" />	
							<c:if test="${prov.equals(user)}">
								<div>
									<form action="finalizarActividad" method="post">
										<div class="form-group">
											<input type="hidden" name="nombreActividad" value="${sessionScope.actividad.nombre}" />
										</div>
										<div>
             								<input type="submit" id="log" class="btn btn-primary btn-block py-2" value="Finalizar actividad">
										</div>
									</form>
								</div>
							</c:if>
							<div class="border-top mt-4 pt-4"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<c:if test="${sessionScope.video != null}">
		<div class="container-fluid py-5" class="embed-responsive-item">
				<div class="embed-responsive embed-responsive-21by9" style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"> 
					<div class="m-0">
						<iframe class="embed-responsive-item"  src="${sessionScope.video}" allowfullscreen>
						</iframe>
					</div>
				</div>
		</div>
	</c:if>

	<div class="container py-4 b">

			<div class="row">
				<div class="col-md-3">
					<!-- Tabs nav -->
					<div class="nav flex-column nav-pills nav-pills-custom" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<a class="nav-link mb-3 p-3 shadow active" id="v-pills-salidas-tab" data-toggle="pill" href="#v-pills-salidas" role="tab" aria-controls="v-pills-salidas" aria-selected="true">
							<i class="fa fa-user-circle-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Salidas</span></a>

						<a class="nav-link mb-3 p-3 shadow" id="v-pills-paquetes-tab" data-toggle="pill" href="#v-pills-paquetes" role="tab" aria-controls="v-pills-paquetes" aria-selected="false">
							<i class="fa fa-calendar-minus-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Paquetes</span></a>

						<a class="nav-link mb-3 p-3 shadow" id="v-pills-categorias-tab" data-toggle="pill" href="#v-pills-categorias" role="tab" aria-controls="v-pills-categorias" aria-selected="false">
							<i class="fa fa-star mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Categorias</span></a>

					</div>
				</div>


				<div class="col-md-9">
					<!-- Tabs content -->
					<div class="tab-content" id="v-pills-tabContent">
						<div class="tab-pane fade shadow rounded bg-white show active p-3" id="v-pills-salidas" role="tabpanel" aria-labelledby="v-pills-salidas-tab">
							<div class="container-fluid py-1">
								<div class="container pt-5 pb-2">
									<div class="text-center mb-3 pb-3">
										<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6>
										<h2>Salidas Turisticas Asociadas</h2>
									</div>
									<div class="row">
										<c:forEach items="${sessionScope.salidasAct}" var="salida" varStatus="loop">
									
										<div class="col-lg-6 col-md-6 mb-4">
											<div class="package-item bg-white mb-2">
											
												<c:set var = "imgSal" scope="session" value="${salida.imgSrc}" />
												<c:if test="${empty imgSal}">
													<c:set var = "imgSal" scope="session" value="${initParam.defaultImgAct}" />
												</c:if>
												<img class="img-fluid" src="Imagenes?id=${imgSal}" alt="" style="height: 180px; width: 90%; margin: 5%; object-fit: cover;">
												<div class="p-4">
													<div class="d-flex justify-content-between mb-3">
														<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${salida.fechaSalida}</small>
														<small class="m-0"><i class="fa fa-clock-o text-primary mr-2"></i>${salida.horaSalida} hs</small>
													</div>
													<div class="d-flex justify-content-between" >
														<small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${salida.lugarSalida}</small>
													</div>
													<div class="text-center justify-content-between mb-3 pt-3">
														<a class="h5 text-decoration-none" href="consultaSalida?salida=${salida.nombre}">${salida.nombre}</a>
													</div>
													<div class="border-top mt-4 pt-4">


													</div>
												</div>
											</div>

										</div>
									    </c:forEach>
										
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-paquetes" role="tabpanel" aria-labelledby="v-pills-paquetes-tab">
							<div class="text-center mb-3 pb-3">
								<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Paquetes</h6>
								<h2>Paquetes Que Incluyen La Actividad</h2>
							</div>
							<div class="row">
								<c:forEach items="${sessionScope.paquetesAct}" var="paquete" varStatus="loop">
								<div class="col-lg-6 col-md-6 mb-4">

									<div class="package-item bg-white mb-2">
									
											
										<c:set var = "imgPaq" scope="session" value="${paquete.imgSrc}" />
										<c:if test="${empty imgPaq}">
											<c:set var = "imgPaq" scope="session" value="${initParam.defaultImgAct}" />
											</c:if>
										<img class="img-fluid" src="Imagenes?id=${imgPaq}" alt="" style="height: 180px; margin: 5%; width: 90%; object-fit: cover;">
										<div class="px-4 py-2">
											<div class="d-flex justify-content-between mb-3">
												<small class="m-0"><i class="fa fa-tag text-primary mr-2"></i>${paquete.descuento}% OFF</small>
												<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${paquete.validez} dias</small>
											</div>
											<div class="text-center justify-content-between mb-3">
												<h5>${paquete.nombre}</h5>
											</div>
											<div class="border-top mt-4 pt-4">
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
								<h2>Categorias asociadas a la actividad</h2>
							</div>
							<c:forEach items="${sessionScope.categoriasAct}" var="cat" varStatus="loop">
							<p>
								<a href="actividades?categorias=${cat}">${cat} </a>
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
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/microplugin/0.0.3/microplugin.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sifter/0.6.0/sifter.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.6/js/selectize.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>


</body>

</html>