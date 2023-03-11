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


    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Paquetes de Actividades</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Paquetes</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


<!-- Paquetes Start -->
<c:set var="from" value="htmls/paquetes.jsp" scope="session"  />

<div class="container-fluid py-5">
    <div class="container pt-5 pb-3">
        <div class="text-center mb-3 pb-3">
            <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Paquetes</h6>
            <h1>Paquetes de actividades</h1>
        </div>
        <div class="row">
        	<c:forEach items="${sessionScope.paquetes}" var="paquete" varStatus="loop">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="package-item bg-white mb-2">
					<c:set var = "img" scope="session" value="${paquete.imgSrc}" />
					<c:if test="${empty img }">
						<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
					</c:if>
                    <img class="img-fluid" src="${initParam.filedir}/${img}" alt="" style="height: 230px; margin: 5%; width: 90%; object-fit: cover;">
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
    </div>
</div>
<!-- Paquetes End -->


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

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>