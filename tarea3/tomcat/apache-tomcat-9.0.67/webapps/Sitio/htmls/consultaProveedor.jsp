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


<!-- Include navbar -->
<%@ include file="navbar.jsp"%>


<!-- Consulta Start -->
<div class="container-fluid p-0">

	<div class="container-fluid py-5">
		<div class="container pt-5">
			<div class="row">
				<div class="col-lg-6" style="min-height: 500px;">
				<c:set var = "imgU" scope="session" value="${requestScope.proveedor.imgSrc}" />
					<c:if test="${empty imgU }">
						<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
					</c:if>
					<div class="position-relative h-100">
						<img class="position-absolute w-100 h-100" src="Imagenes?id=${imgU}" style="object-fit: cover;" alt="">
					</div>
				</div>
				<div class="col-lg-6 pt-5 pb-lg-5">
					<div class="about-text bg-white p-4 p-lg-5 my-lg-5">
						<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Proveedor</h6>
						<h1 class="mb-3">${requestScope.proveedor.nombre} ${requestScope.proveedor.apellido}</h1>
						<div class="p-4">
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-user text-primary mr-2" style="font-size: large"></i>${requestScope.proveedor.nickname}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-link text-primary mr-2" style="font-size: large"></i><a href="${requestScope.proveedor.link}">${requestScope.proveedor.link}</a></div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-envelope text-primary mr-2"  style="font-size: large"></i>${requestScope.proveedor.email}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-birthday-cake text-primary mr-2"  style="font-size: large"></i>${requestScope.proveedor.nacimiento}</div>
							</div>
							<div class="d-flex justify-content-between">
								<div class="m-0"><i class="fa fa-newspaper-o mr-2 text-primary" style="font-size: large" aria-hidden="true"></i>${requestScope.proveedor.descripcion}</div>
							</div>
							<c:if test="${sessionScope.usuario_logueado == requestScope.proveedor.nickname}">
                            <div class="input-group-append">
                            	
                                <button class="btn btn-primary px-3" onclick="location.href='modificarUsuario?usuario=${requestScope.proveedor.nickname}'" type="submit">Modificar datos</button>
                            </div>
							</c:if>
							<div class="p-4">
							<div class="d-flex justify-content-between mb-3">
								<h5></h5>
								<div class="text-center mb-500 pb-100">
									<h5><span id="segs">${sessionScope.cantSeguidores}</span></h5>
									<h5>Seguidores</h5>
								</div>
								<div class="text-center mb-500 pb-500">
									<h5>${sessionScope.cantSeguidos}</h5>
									<h5>Seguidos</h5>
								</div>
								<h5></h5>
							</div>
							</div>
							<c:if test="${not empty sessionScope.usuario_logueado}">
                            	<c:if test="${sessionScope.usuario_logueado != requestScope.proveedor.nickname}">
                            		<c:if test="${not sessionScope.sigue}" >
                            			<div class="text-center mb-3 pb-3" id="btnSeguir">
                                			<button class="btn btn-primary px-3" >Seguir</button>
                            			</div>
                                		<div class="text-center mb-3 pb-3"  id="btnNoSeguir" style="display: none">
                                			<button class="btn btn-primary px-3">Dejar de seguir</button>
                            			</div>"
                            		</c:if>
                            		<c:if test="${sessionScope.sigue}" >
                            			<div class="text-center mb-3 pb-3" id="btnSeguir2" style="display: none">
                                			<button class="btn btn-primary px-3"  >Seguir</button>
                            			</div>
                                		<div class="text-center mb-3 pb-3" id="btnNoSeguir2">
                                			<button class="btn btn-primary px-3" >Dejar de seguir</button>
                            			</div>
                            		</c:if>
                            		
                              	</c:if>
                        	</c:if>
							<!-- <div class="border-top mt-4 pt-4"></div> -->
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
						<a class="nav-link mb-3 p-3 shadow active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
							<i class="fa fa-user-circle-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Actividades</span></a>

						<a class="nav-link mb-3 p-3 shadow" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
							<i class="fa fa-calendar-minus-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Salidas</span></a>


					</div>
				</div>


				<div class="col-md-9">
					<!-- Tabs content -->
					<div class="tab-content" id="v-pills-tabContent">
						<div class="tab-pane fade shadow rounded bg-white show active p-3" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
							<div class="container-fluid py-1">
								<div class="container pt-5 pb-2">
									<div class="text-center mb-3 pb-3">
										<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6>
										<h2>Actividades Turísticas Ofrecidas</h2>
									</div>
									<div class="row">
									<c:forEach items="${requestScope.actividades}" var="act" varStatus="loop">
										<div class="col-lg-6 col-md-6 mb-4">
											<div class="package-item bg-white mb-2">
												<c:set var = "imgA" scope="session" value="${act.imageSrc}" />
												<c:if test="${empty imgA }">
													<c:set var = "imgA" scope="session" value="${initParam.defaultImgAct}" />
												</c:if>
												<img class="img-fluid" src="Imagenes?id=${imgA}" alt="" style="height: 180px; width: 90%; margin: 5%; object-fit: cover;">
												<div class="p-4">
													<div class="d-flex justify-content-between mb-3">
														<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${act.fechaAlta}</small>
														<small class="m-0"><i class="fa fa-clock-o text-primary mr-2"></i>${act.duracion} hs</small>
														<small class="m-0"><i class="fa fa-money text-primary mr-2"></i>${act.costo}</small>
													</div>
													<div class="d-flex justify-content-between" >
														<small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${act.departamento.nombre}, ${act.ciudad}</small>
													</div>
													
													<c:if test="${sessionScope.usuario_logueado == requestScope.proveedor.nickname}">
														<div class="d-flex justify-content-between mb-3">
															<div class="m-0"><i class="fa fa-info-circle text-primary mr-2"></i>${act.estado}</div>
														</div>
													</c:if>
													<div class="text-center justify-content-between mb-3 pt-3">
														<a class="h5 text-decoration-none" href="consultaActividad?actividad=${act.nombre}">${act.nombre}</a>
													</div>
													<div class="border-top mt-4 pt-4"></div>
												</div>
											</div>
										</div>
							        </c:forEach>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
							<div class="text-center mb-3 pb-3">
								<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6>
								<h2>Salidas Incluidas En Las Actividades</h2>
							</div>
							<div class="row">
								
								
								<c:forEach items="${requestScope.salidas}" var="sal" varStatus="loop">
									<div class="col-lg-6 col-md-6 mb-4">
									<div class="package-item bg-white mb-2">
									<c:set var = "imgA" scope="session" value="${sal.imgSrc}" />
									<c:if test="${empty imgA }">
										<c:set var = "imgA" scope="session" value="${initParam.defaultImgAct}" />
									</c:if>
										<img class="img-fluid" src="Imagenes?id=${imgA}"  alt="" style="height: 180px; width: 90%; margin: 5%; object-fit: cover;">
												<div class="p-4">
													<div class="d-flex justify-content-between mb-3">
														<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${sal.fechaSalida}</small>
														<small class="m-0"><i class="fa fa-clock-o text-primary mr-2"></i>${sal.horaSalida} hs</small>
														
													</div>
													<div class="d-flex justify-content-between" >
														<small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${sal.lugarSalida}</small>
													</div>
													<div class="text-center justify-content-between mb-3 pt-3">
														<a class="h5 text-decoration-none" href="consultaSalida?salida=${sal.nombre}">${sal.nombre}</a>
													</div>
													<div class="border-top mt-4 pt-4"></div>
												</div>
									</div>
									</div>
								</c:forEach>
								

								
							</div>
							
							
								
							
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
    <script src="js/seguir.js"></script>
    <script src="js/dejarSeguir.js"></script>
    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>
