<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<html lang="en">

<head>
    <meta charset="utf-8">
    <title>TURISMO.UY</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
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
	<link href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.default.css" rel="stylesheet" />


    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">


</head>

<body>


<!-- Include navbar -->


<%@ include file="navbar.jsp"%>

<c:set var="UPLOAD_DIRECTORY"  value="${initParam.filedir}"/>
<c:set var="from" value="htmls/index.jsp" scope="session"  />

<!-- Carousel Start -->
<div class="container-fluid p-0">
    <div id="header-carousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="w-100" src="${UPLOAD_DIRECTORY}/carousel-1.jpg" alt="Image" >
                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    <div class="p-3" style="max-width: 900px;">
                        <h4 class="text-white text-uppercase mb-md-3">Viajes y Turismo</h4>
                        <h1 class="display-3 text-white mb-md-4">Disfruta Unas Vacaciones Inolvidables</h1>
                        <a href="paquetes" class="btn btn-primary py-md-3 px-md-5 mt-2">Explorar Paquetes</a>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="w-100" src="${UPLOAD_DIRECTORY}/carousel-2.jpg" alt="Image">
                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                    <div class="p-3" style="max-width: 900px;">
                        <h4 class="text-white text-uppercase mb-md-3">Viajes y Turismo</h4>
                        <h1 class="display-3 text-white mb-md-4">Descubra Uruguay Con Nosotros</h1>
                        <a href="paquetes" class="btn btn-primary py-md-3 px-md-5 mt-2">Explorar Paquetes</a>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
            <span class="carousel-control-prev-icon mb-n2"></span>
        </a>
        <a class="carousel-control-next" href="#header-carousel" data-slide="next">
            <span class="carousel-control-next-icon mb-n2"></span>
        </a>
    </div>
</div>
<!-- Carousel End -->

   

    <!-- Actividades Start -->
    <div class="container-fluid py-5">
        <div class="container pt-5 pb-3">
            <div class="text-center mb-3 pb-3">
                <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Actividades</h6>
                <h1>Actividades turísticas</h1>
            </div>
            <div class="owl-carousel testimonial-carousel">
               	<c:forEach items="${sessionScope.actividades}" var="act" varStatus="loop">
            
                <div class="text-center pb-4">
                    <div class="package-item bg-white mb-2">
                    <c:set var = "img" scope="session" value="${act.imageSrc}" />
					<c:if test="${empty img }">
						<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
					</c:if>
                        <img class="img-fluid" src="${UPLOAD_DIRECTORY}/${img}" alt="" style="height: 230px; object-fit: cover;">
                        <div class="p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${act.departamento}, ${act.ciudad}</small>
                                <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${act.duracion} horas</small>
                            </div>
                            <a class="h5 text-decoration-none" href="consultaActividad?actividad=${act.nombre}">${act.nombre}</a>
                            <div class="border-top mt-4 pt-4">
                                <div class="d-flex justify-content-between" style="padding-bottom: 10px">
                                    <h5 class="m-0">$${act.costo}</h5>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <small class="m-0"><i class="fa fa-newspaper-o" aria-hidden="true"></i> ${act.descripcion}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="text-right mb-3 pb-3">
                <a class="text-primary text-uppercase" href="actividades" style="letter-spacing: 1px; ">Ver todas las Actividades</a>
            </div>
        </div>
    </div>
    <!-- Actividades End -->


    <!-- Paquetes Start -->
    <div class="container-fluid py-5">
        <div class="container pt-5 pb-3">
            <div class="text-center mb-3 pb-3">
                <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Paquetes</h6>
                <h1>Paquetes de actividades turísticas</h1>
            </div>
            <div class="owl-carousel testimonial-carousel">
            	<c:forEach items="${sessionScope.paquetes}" var="paquete" varStatus="loop">
                <div class="text-center pb-4">
                    <div class="package-item bg-white mb-2">
                    	<c:set var = "img" scope="session" value="${paquete.imgSrc}" />
						<c:if test="${empty img }">
							<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
						</c:if>
                        <img class="img-fluid" src="${UPLOAD_DIRECTORY}/${img}" alt="" style="height: 230px; object-fit: cover;">
                        <div class="p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="fa fa-tag text-primary mr-2"></i>${paquete.descuento}% OFF</small>                         
                                <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${paquete.validez} días</small>
                            </div>
                            <a class="h5 text-decoration-none" href="ConsultaPaquete?paquete=${paquete.nombre}">${paquete.nombre}</a>
                            <div class="border-top mt-4 pt-4">

                            </div>
                            <div class="d-flex justify-content-between">
                                    <small class="m-0"><i class="fa fa-newspaper-o" aria-hidden="true"></i> ${paquete.descripcion}</small>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="text-right pb-4">
                <a class="text-primary text-uppercase" href="paquetes" style="letter-spacing: 1px;">Ver todos los Paquetes</a>
            </div>
        </div>
    </div>
    <!-- Paquetes End -->

	<!-- Salidas Start -->
    <div class="container-fluid py-5">
        <div class="container pt-5 pb-3">
            <div class="text-center mb-3 pb-3">
                <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6>
                <h1>Salidas turísticas</h1>
            </div>
            <div class="owl-carousel testimonial-carousel">
            	<c:forEach items="${sessionScope.salidas}" var="salida" varStatus="loop">
                <div class="text-center pb-4">
                    <div class="package-item bg-white mb-2">
                    	<c:set var = "img" scope="session" value="${salida.imgSrc}" />
						<c:if test="${empty img }">
							<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
						</c:if>
                        <img class="img-fluid" src="${UPLOAD_DIRECTORY}/${img}" alt="" style="height: 230px; object-fit: cover;">
                        <div class="p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${salida.fechaSalida}</small>
                                <small class="m-0"><i class="fa fa-clock-o text-primary mr-2"></i>${salida.horaSalida} hs</small>
                            </div>
                            <a class="h5 text-decoration-none" href="consultaSalida?salida=${salida.nombre}">${salida.nombre}</a>
                            <div class="border-top mt-4 pt-4">
                                <div class="d-flex justify-content-between">
                                    <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${salida.lugarSalida}</small>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
                
                </c:forEach>
                
            </div>
            <div class="text-right mb-3 pb-3">
                <a class="text-primary text-uppercase" href="salidas" style="letter-spacing: 1px; ">Ver todas las Salidas</a>
            </div>
        </div>
    </div>	
    
    <!-- Salidas End -->
    


    <!-- Proveedores Start -->
    <div class="container-fluid py-5">
        <div class="container pt-5 pb-3">
            <div class="text-center mb-3 pb-3">
                <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Proveedores</h6>
                <h1>Nuestros proveedores</h1>
            </div>
            <div class="owl-carousel testimonial-carousel">
               	<c:forEach items="${sessionScope.proveedores}" var="prov" varStatus="loop">
            
                <div class="text-center pb-4">
                    <div class="team-item bg-white mb-4">
                        <div class="team-img position-relative overflow-hidden">
                        	<c:set var = "img" scope="session" value="${prov.imgSrc}" />
							<c:if test="${empty img }">
								<c:set var = "img" scope="session" value="${initParam.defaultImgUser}" />
							</c:if>
                            <img class="img-fluid w-100" src="${UPLOAD_DIRECTORY}/${img}" alt="" style="height: 230px; object-fit: cover;">

                            <div class="team-social">
                                <a class="btn btn-outline-primary btn-square" href="consultaUsuario?usuario=${prov.nickname}"><i class="fa fa-link"></i></a>
                            </div>
                        </div>
                        <div class="testimonial-text bg-white p-4 mt-n5">
                              
                            <p class="mt-5">
                            </p>
                           
                            <h5 class="text-truncate">${prov.nombre} ${prov.apellido}</h5>
                            <span> <small class="m-0"><i class="fa fa-user-circle" aria-hidden="true"></i>${prov.nickname}</small> </span>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
            <div class="text-right mb-3 pb-3">
                <a class="text-primary text-uppercase" href="consultaUsuario?tipo=proveedores" style="letter-spacing: 1px;">Ver todos los Proveedores</a>
            </div>
        </div>
    </div>
    <!-- Proveedores End -->



    <!-- Include Footer -->

<%@ include file="footer.jsp"%>


    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->

	<script src="js/jquery-3.6.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/microplugin/0.0.3/microplugin.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sifter/0.6.0/sifter.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.6/js/selectize.min.js"></script>
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
