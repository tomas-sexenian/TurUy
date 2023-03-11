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

<!-- Header Start -->
<div class="container-fluid page-header">
    <div class="container">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
            <h3 class="display-4 text-white text-uppercase">Consulta de Salida</h3>
            <div class="d-inline-flex text-white">
                <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase"><a class="text-white" href="salidas">Salidas</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase">Consulta de Salida</p>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- COnsulta start -->
<c:set var="from" value="consultaSalida?salida=${sessionScope.salida.nombre}" scope="session"  />
<c:set var="nombreSalida" value="${sessionScope.salida.nombre}" scope="session"  />

<div class="container-fluid py-5">
    <div class="container pt-5">
        <div class="row">
            <div class="col-lg-6" style="min-height: 500px;">
            
                <c:set var = "img" scope="session" value="${sessionScope.salida.imgSrc}" />
				<c:if test="${empty img }">
					<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
				</c:if>
                <div class="position-relative h-100">
                    <img class="position-absolute w-100 h-100" src="${initParam.filedir}/${img}" style="object-fit: cover;" alt="">
                </div>
            </div>
            <div class="col-lg-6 pt-5 pb-lg-5">
                <div class="about-text bg-white p-4 p-lg-5 my-lg-5">
                    <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salida</h6>
                    <h1 class="mb-3">${sessionScope.salida.nombre}</h1>
                    <div class="p-4">
                        <div class="d-flex justify-content-between mb-3">
                            <div class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2" style="font-size: larger"></i>${sessionScope.salida.lugarSalida}</div>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <div class="m-0"><i class="fa fa-calendar-alt text-primary mr-2" style="font-size: large"></i>${sessionScope.salida.fechaSalida}</div>
                        </div>

                        <div class="d-flex justify-content-between mb-3">
                            <div class="m-0"><i class="fa fa-calendar-alt text-primary mr-2" style="font-size: large"></i>${sessionScope.salida.horaSalida} horas</div>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h5 class="m-0">Lugares disponibles: ${sessionScope.salida.lugaresDisponibles}</h5>
                        </div>
                        <c:set var = "vigente" scope="session" value="${sessionScope.vigente}" />
                        <c:set var = "lugares" scope="session" value="${sessionScope.salida.lugaresDisponibles}" />
                        <c:if test="${empty user and not empty vigente and lugares gt 0}">
                        	<div class="d-flex justify-content-between pt-3">
	                        	<div class="m-0"><a href="altaUsuario">Registrarse</a> o <a href="Login">iniciar sesion</a> para inscribirse</div>
	                    	</div>
                        </c:if>

						<c:if test="${not empty user and not empty vigente and lugares gt 0}" >
							<c:set var = "tipo" scope="session" value="${sessionScope.tipo_usuario}" />
							<c:if test="${tipo eq 'Proveedor'}">
                        		<div class="d-flex justify-content-between pt-3">
	                        		<div class="m-0"><i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: large"></i>Para inscribirse es necesario iniciar sesión como turista</div>
	                    		</div>
                        	</c:if>
							
							<c:if test="${tipo eq 'Turista' }" >
								<button class="btn btn-primary btn-block" data-toggle="dropdown" style="height: 47px; margin-top: 20px;">Inscribirse</button>
								
								<div class="col-lg-8 dropdown-menu dropdown-menu-high" >
                            		<div class="card border-0">

                                		<div class="card-body rounded-bottom bg-white p-5">
                                    		<form id="inscripcion" action="inscripcionSalida" method="post" >

                                        		<div class="form-group">
                                            		<input type="number" class="form-control px-4" id="cTuristas" name="cTuristas"  min="1" max="${sessionScope.salida.lugaresDisponibles}" style="height: 47px" placeholder="Cantidad de turistas" required="required"/>
                                        		</div>
                                        		
                                        		<c:set var = "tienePaquetes" scope="request" value="${sessionScope.tienePaquetes}" />
                                        		<c:if test="${not empty tienePaquetes}">
                                        		<div class="form-group">
                                            		<select class="custom-select px-4" style="height: 47px;" id="formaPago" name="formaPago" required="required">
                                                		<option selected value="0" disabled hidden="true" onchange="selectorPago">Forma de pago</option>
                                                		<option value="0">General</option>
                                                		<option value="1">Paquete</option>
                                            		</select>
                                            		<script type="text/javascript">
                                            			document.getElementById('formaPago').onchange = function () {
                                            		    	if (this.value == '0') {
                                            			    	document.getElementById("paquete").selectedIndex=0;
                                            		        	document.getElementById("paquete").disabled = true;
                                            		        	document.getElementById("paquete").required = false;
                                            		    	}
                                            		    	else {
                                            			    	document.getElementById("paquete").disabled = false;
                                            		        	document.getElementById("paquete").required = true;
                                            		    	}
                                            			}
                                            		</script>
                                        		</div>
                                        		<div class="form-group">
                                            		<select class="custom-select px-4" id="paquete" name="paquete" style="height: 47px;" disabled="disabled">
                                                		<option selected value="">Seleccionar Paquete</option>
                                                		<c:forEach items="${sessionScope.paquetesUsuario}" var="pqt" varStatus="loop">
                                                			<option value="${pqt.nombre}">${pqt.nombre}</option>
                                                		</c:forEach>
                                                		
                                            		</select>
                                            		
                                        		</div>
                                        		
                                        		</c:if>
                                        		<div>
                                            		<button class="btn btn-primary btn-block py-3" type="submit">Confirmar Inscripcion</button>
                                        		</div>
                                    		</form>
                                		</div>
                            		</div>
                        		</div>
							</c:if>
						</c:if>
						
						

                        <div class="border-top mt-4 pt-4"></div>
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