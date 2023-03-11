<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


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
                <h3 class="display-4 text-white text-uppercase">Actividades Turisticas</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                                   
                    
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Actividades</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


<!-- Buscador Start -->
<c:set var="from" value="htmls/actividades.jsp" scope="session"  />

<c:set var = "deptoSelected" scope="request" value="${sessionScope.departamento}" />
<c:set var = "catSelected" scope="request" value="${sessionScope.categoriaSelected}" />

<div class="container-fluid booking mt-5 pb-5">
        <div class="container pb-5">
            <div class="bg-light shadow" style="padding: 30px;">
                <div class="row align-items-center" style="min-height: 60px;">
                    <div class="col-md-10">
                        <form action="actividades" id ="act">
                        <div class="row">
                        
                            <div class="col-md-6">
                                <div class="mb-3 mb-md-0">
                                    <select name="departamento" id="departamento" class="custom-select px-4" style="height: 47px;">
                                    	
                                    	<c:if test="${empty deptoSelected}">
                                    		<option value="" selected >Departamento</option>
                                    	</c:if>
                                    	<c:forEach items="${applicationScope.departamentos}" var="depto" varStatus="loop">
                                    		<option <c:if test="${deptoSelected eq depto}"> selected</c:if> value='${depto}'>${depto}</option>
                                    	</c:forEach>
                                    
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3 mb-md-0">
                                    <select name="categorias" id="categorias" class="custom-select px-4" style="height: 47px;">
                                       	
                                    	<c:if test="${empty catSelected}">
                                    		<option value="" selected >Categoria</option>
                                    	</c:if>
                                    	<c:forEach items="${applicationScope.categorias}" var="cate" varStatus="loop">
                                    		<option <c:if test="${catSelected eq cate}"> selected</c:if> value='${cate}'>${cate} </option>
                                    	</c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary btn-block" form="act" type="submit" style="height: 47px; margin-top: -2px;">Buscar</button>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    <!-- Buscador End -->
    


<!-- Actividades Start -->

<div class="container-fluid py-5">
    <div class="container pt-5 pb-3">
        <div class="text-center mb-3 pb-3">
            <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Actividades</h6>
            <h1>Actividades turisticas</h1>
        </div>
        <div class="row">
           	<c:forEach items="${sessionScope.actividades}" var="act" varStatus="loop">
        
            <div class="col-lg-6 col-md-6 mb-4">
                <div class="package-item bg-white mb-2">
                	<c:set var = "img" scope="session" value="${act.imageSrc}" />
					<c:if test="${empty img }">
						<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
					</c:if>
                    <img class="img-fluid" src="Imagenes?id=${img}" alt="" style="height: 230px; margin: 5%; width: 90%; object-fit: cover;">
                    <div class="p-4">
                        <div class="d-flex justify-content-between mb-3">
                            <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${act.departamento.nombre}</small>
                            <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${act.duracion} horas</small>
                        </div>
                        <a class="h5 text-decoration-none" href="consultaActividad?actividad=${act.nombre}">${act.nombre}</a>
                        <div class="border-top mt-4 pt-4">
                            <div class="d-flex justify-content-between">
                                <h5 class="m-0">$${act.costo}</h5>
                            </div>
                        </div>
                        <div class="col-md-17">
                            <p>
                                <button class="btn btn-primary btn-block" onclick="location.href='consultaActividad?actividad=${act.nombre}'" type="submit" style="height: 47px; margin-top: 20px;">Mas informacion</button>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
            </c:forEach>
            
        </div>
    </div>
</div>
<!-- Actividades End -->


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
    
</body>

</html>