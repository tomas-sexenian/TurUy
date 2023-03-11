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
				<c:set var = "imgU" scope="session" value="${requestScope.turista.imgSrc}" />
					<c:if test="${empty imgU }">
						<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
					</c:if>
					<div class="position-relative h-100">
						<img class="position-absolute w-100 h-100" src="Imagenes?id=${imgU}" style="object-fit: cover;" alt="">
					</div>
				</div>
				<div class="col-lg-6 pt-5 pb-lg-5">
					<div class="about-text bg-white p-4 p-lg-5 my-lg-5">
						<h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Turista</h6>
						<h1 class="mb-3">${requestScope.turista.nombre} ${requestScope.turista.apellido}</h1>
						
						<div class="p-4">
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-user text-primary mr-2" style="font-size: large"></i>${requestScope.turista.nickname}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-id-card text-primary mr-2" style="font-size: large"></i>${requestScope.turista.nacionalidad}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-envelope text-primary mr-2"  style="font-size: large"></i>${requestScope.turista.email}</div>
							</div>
							<div class="d-flex justify-content-between mb-3">
								<div class="m-0"><i class="fa fa-birthday-cake text-primary mr-2"  style="font-size: large"></i>${requestScope.turista.nacimiento}</div>
							</div>
							<c:if test="${sessionScope.usuario_logueado == requestScope.turista.nickname}">
                            <div class="input-group-append">
                                <button class="btn btn-primary px-3" onclick="location.href='modificarUsuario?usuario=${requestScope.turista.nickname}'" type="submit">Modificar datos</button>
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
							<c:set var = "usuario" scope="request" value="${requestScope.turista.nickname}" />
							<c:if test="${not empty sessionScope.usuario_logueado}">
                            	<c:if test="${sessionScope.usuario_logueado != requestScope.turista.nickname}">
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
						<a class="nav-link mb-3 p-3 shadow active" id="v-pills-inscripciones-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">
							<i class="fa fa-user-circle-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Inscripciones</span></a>

						<a class="nav-link mb-3 p-3 shadow" id="v-pills-paquetes-tab" data-toggle="pill" href="#v-pills-profile" role="tab" aria-controls="v-pills-profile" aria-selected="false">
							<i class="fa fa-calendar-minus-o mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Paquetes comprados</span></a>
						
						<a class="nav-link mb-3 p-3 shadow" id="v-pills-seguidores-tab" data-toggle="pill" href="#v-pills-seguidores" role="tab" aria-controls="v-pills-seguidores" aria-selected="false">
							<i class="fa fa-heart mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Seguidores</span></a>
						
						<a class="nav-link mb-3 p-3 shadow" id="v-pills-seguidos-tab" data-toggle="pill" href="#v-pills-seguidos" role="tab" aria-controls="v-pills-seguidos" aria-selected="false">
							<i class="fa fa-heart mr-2"></i>
							<span class="font-weight-bold small text-uppercase">Seguidos</span></a>

					</div>
				</div>


				<div class="col-md-9">
					<!-- Tabs content -->
					<div class="tab-content" id="v-pills-tabContent">
						<div class="tab-pane fade shadow rounded bg-white show active p-3" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-inscripciones-tab">
							<div class="container-fluid py-1">
								<div class="container pt-5 pb-2">
									<div class="text-center mb-3 pb-3">
										<!-- <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6> -->
										<h2>Inscripciones</h2>
									</div>
									<div class="row">
									<c:forEach items="${requestScope.inscripciones}" var="insc" varStatus="loop">
									
										<div class="col-lg-6 col-md-6 mb-4">
											<div class="package-item bg-white mb-2">
												<c:set var = "imgA" scope="session" value="${insc.salida.imgSrc}" />
												<c:if test="${empty imgA }">
													<c:set var = "imgA" scope="session" value="${initParam.defaultImgAct}" />
												</c:if>
												<img class="img-fluid" src="Imagenes?id=${imgA}" alt="" style="height: 180px; width: 90%; margin: 5%; object-fit: cover;">
												<div class="p-4">
													<div class="d-flex justify-content-between mb-3">
														<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${insc.fechaInscripcion}</small>
														<small class="m-0"><i class="fa fa-money text-primary mr-2"></i>$${insc.costo}</small>
													</div>
													<div class="d-flex justify-content-between" >
														<c:if test="${sessionScope.usuario_logueado == requestScope.turista.nickname}">
														
														<small class="m-0"><i class="fa fa-file-pdf text-primary mr-2"></i><a download="${sessionScope.usuario_logueado}-${insc.salida.nombre}.pdf" href="/Sitio/inscripcionSalida?usuario=${sessionScope.usuario_logueado}&salida=${insc.salida.nombre}">Descargar pdf</a></small>
														</c:if>
														<small class="m-0"><i class="fa fa-users text-primary mr-2"></i>${insc.cantTuristas}</small>
													</div>
													<div class="text-center justify-content-between mb-3 pt-3">
														<a class="h5 text-decoration-none" href="consultaSalida?salida=${insc.salida.nombre}">${insc.salida.nombre}</a>
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

						<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-paquetes-tab">
							<div class="text-center mb-3 pb-3">
								<h2>Paquetes comprados</h2>
							</div>
							<div class="row">
							<c:forEach items="${requestScope.compras}" var="compra" varStatus="loop">
							
								<div class="col-lg-6 col-md-6 mb-4">
									<c:set var = "imgA" scope="session" value="${compra.paquete.imgSrc}" />
									<c:if test="${empty imgA }">
										<c:set var = "imgA" scope="session" value="${initParam.defaultImgAct}" />
									</c:if>
									<div class="package-item bg-white mb-2">
										<img class="img-fluid" src="Imagenes?id=${imgA}" alt="" style="height: 180px; margin: 5%; width: 90%; object-fit: cover;">
										<div class="px-4 py-2">
											<div class="d-flex justify-content-between mb-3">
												<small class="m-0"><i class="fa fa-users text-primary mr-2"></i>${compra.cantTuristas}</small>
												<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${compra.fechaCompra}</small>
											</div>
											<div class="d-flex justify-content-between" >
												
												<small class="m-0"><i class="fa fa-hourglass text-primary mr-2"></i>${compra.validez}</small>
												<small class="m-0"><i class="fa fa-money text-primary mr-2"></i>$${compra.costo}</small>
											</div>
											<div class="text-center justify-content-between mb-3 pt-3">
												<a class="h5 text-decoration-none" href="ConsultaPaquete?paquete=${compra.paquete.nombre}">${compra.paquete.nombre}</a>
											</div>
											<div class="border-top mt-4 pt-4">
											</div>
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
						
						<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-seguidores" role="tabpanel" aria-labelledby="v-pills-seguidores-tab">
							<div class="text-center mb-3 pb-3">
								<h2>Seguidores</h2>
							</div>
							<div class="row">
							
							<c:forEach items="${sessionScope.seguidores_prov}" var="proveedor" varStatus="loop">
								<div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
		            				<c:set var = "imgU" scope="session" value="${proveedor.imgSrc}" />
									<c:if test="${empty imgU }">
										<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
									</c:if>	                    			
	                    			<img class="img-fluid mx-auto" src="Imagenes?id=${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                    			
	                    			<div class="testimonial-text bg-white p-4 mt-n5">
	                        			<p class="mt-5"></p>
	                        			<a class="h5 text-decoration-none" href="consultaUsuario?usuario=${proveedor.nickname}">${proveedor.nombre} ${proveedor.apellido}</a><br>
	                        			<span>Proveedor</span>
	                    			</div>
	                    			
	                			</div>
							</c:forEach>
							
							<c:forEach items="${sessionScope.seguidores_tur}" var="turista" varStatus="loop">
								<div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
	                			<c:set var = "imgU" scope="session" value="${turista.imgSrc}" />
								<c:if test="${empty imgU }">
									<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
								</c:if>
	                			<img class="img-fluid mx-auto" src="Imagenes?id=${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                			<div class="testimonial-text bg-white p-4 mt-n5">
	                    			<p class="mt-5"></p>
	                    			<a class="h5 text-decoration-none" href="consultaUsuario?usuario=${turista.nickname}">${turista.nombre} ${turista.apellido}</a><br>
	                    			<span>Turista</span>
	                			</div>
	                			</div>
							</c:forEach>
							
							</div>
						</div>
						
						<div class="tab-pane fade shadow rounded bg-white p-5" id="v-pills-seguidos" role="tabpanel" aria-labelledby="v-pills-seguidos-tab">
							<div class="text-center mb-3 pb-3">
								<h2>Seguidos</h2>
							</div>
							<div class="row">
							
							
							<c:forEach items="${sessionScope.seguidos_prov}" var="proveedor" varStatus="loop">
								<div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
		            				<c:set var = "imgU" scope="session" value="${proveedor.imgSrc}" />
									<c:if test="${empty imgU }">
										<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
									</c:if>
	                    			<img class="img-fluid mx-auto" src="Imagenes?id=${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                    			<div class="testimonial-text bg-white p-4 mt-n5">
	                        			<p class="mt-5"></p>
	                        			<a class="h5 text-decoration-none" href="consultaUsuario?usuario=${proveedor.nickname}">${proveedor.nombre} ${proveedor.apellido}</a><br>
	                        			<span>Proveedor</span>
	                    			</div>
	                			</div>
							</c:forEach>
							
							<c:forEach items="${sessionScope.seguidos_tur}" var="turista" varStatus="loop">
								<div class="text-center pb-4 col-lg-4 col-md-6 mb-4">
	                			<c:set var = "imgU" scope="session" value="${turista.imgSrc}" />
								<c:if test="${empty imgU }">
									<c:set var="imgU" scope="session" value="${initParam.defaultImgUser}" />
								</c:if>
	                			<img class="img-fluid mx-auto" src="Imagenes?id=${imgU}" style="width: 100px; height: 100px; object-fit: cover;" >
	                			<div class="testimonial-text bg-white p-4 mt-n5">
	                    			<p class="mt-5"></p>
	                    			<a class="h5 text-decoration-none" href="consultaUsuario?usuario=${turista.nickname}">${turista.nombre} ${turista.apellido}</a><br>
	                    			<span>Turista</span>
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
