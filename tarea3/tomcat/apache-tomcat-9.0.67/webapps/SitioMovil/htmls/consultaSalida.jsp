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
            <h3 class="display-4 text-white text-uppercase text-center">Consulta de Salida</h3>
            <div class="d-inline-flex text-white">
                <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
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
                    <img class="position-absolute w-100 h-100" src="Imagenes?id=${img}" style="object-fit: cover;" alt="">
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
                            <h5 class="m-0 h6">Lugares disponibles: ${sessionScope.salida.lugaresDisponibles}</h5>
                        </div>
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

</body>

</html>