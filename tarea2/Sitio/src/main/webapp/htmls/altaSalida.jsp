<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@page import="java.time.LocalDate"%>

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
    <!-- Includes -->
</head>

<body>

<!-- Include navbar -->
<%@ include file="navbar.jsp"%>

<c:set var = "msg" scope="session" value="${alert}" />
<c:set var = "error" scope="request" value="${error}" />

<c:if test="${not empty msg }">

 	<script type="text/javascript">
 		var msg = '${alert}';
 		function alertName(){
 			alert(msg);
 		}
 		window.onload = alertName;
 	</script> 
</c:if>

    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Alta de Salida Turistica</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Alta de Salida Turistica</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->




    <!-- Campos Start -->
    <div class="container-fluid booking mt-5 pb-5">
        <div class="container pb-5">
            <div class="bg-light shadow" style="padding: 30px;">
                <div class="row align-items-center" style="min-height: 60px; justify-content: center;">
                    <div class="col-md-10" style="justify-content: center;">
                        <div class="row;text-align:center;">
                        
                        	<form id="formdepto" action="altaSalida" method="post">
                        		<!-- Departamentos-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                <label for="fdept">Departamento:</label>
                                <select class="custom-select px-4" onchange=formdepto.submit() style="height: 47px;" id="fdept" name="fdept">
                                	<c:set var = "deptoSelected" scope="request" value="${deptoSelected}" />
	
                                    <c:if test="${empty deptoSelected}">
                                    	<option value="" disabled selected hidden="true">Seleccionar...</option>
                                    </c:if>
                                    
                                    <c:forEach items="${applicationScope.departamentos}" var="depto" varStatus="loop">
                                    	<option <c:if test="${deptoSelected eq depto}"> selected</c:if> value='${depto}'>${depto}</option>
                                    </c:forEach>
                                </select>
                                </div>
                        	</form>
                        	
                            <form id="alta" method="post" action="altaSalida" enctype="multipart/form-data">
                                <!--Actividad-->
                                <div id="salidasAlta" class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="actividad">Actividad:</label>
                                    <select class="custom-select px-4" style="height: 47px;" id="actividad" name="actividad" required="required" >
										<c:set var = "actividadAltaSalida" scope="request" value="${actividadAltaSalida}" />
                                    	<c:if test="${empty error }">
                                    		<option value="" disabled selected hidden="true">Seleccionar...</option>
                                    	</c:if>
                                        <c:forEach items="${requestScope.actividades}" var="act" varStatus="loop">
                                    		<option <c:if test="${actividadAltaSalida eq act.nombre }"> selected</c:if> value='${act.nombre}'>${act.nombre}</option>
                                    	</c:forEach>
                                    </select>
                                </div>

                                <!--Nombre-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="nombre">Nombre de la salida:</label>
                                    <input type="text" class="form-control px-4" id="nombre" name="nombre" style="height: 47px" placeholder=""  required="required"  />
                                </div>

                                <!--Fecha-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="fecha">Fecha:</label>
                                    <input type="date" class="form-control px-2" id="fecha" name="fecha"  min="1" style="height: 47px" placeholder="" required="required" <c:if test="${not empty error }"> value="${fechaAltaSalida }"</c:if>/>
                                </div>

                                <!--Hora-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="hora">Hora:</label>
                                    <input type="time" class="form-control px-4" id="hora" name="hora" style="height: 47px" placeholder=""  required="required" <c:if test="${not empty error }"> value="${horaAltaSalida }"</c:if>  />
                                </div>

                                <!--Costo-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lugar">Lugar de salida:</label>
                                    <input type="text" class="form-control px-2" id="lugar" name="lugar"  min="1" style="height: 47px" placeholder=""  required="required" <c:if test="${not empty error }"> value="${lugarAltaSalida }"</c:if>/>
                                </div>

                                <!--Cantidad de turistas-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="cTuristas">Cantidad maxima de turistas:</label>
                                    <input type="number" class="form-control px-2" id="cTuristas" name="cTuristas"  min="1" style="height: 47px" placeholder=""  required="required" <c:if test="${not empty error }"> value="${cantMax }"</c:if>/>
                                </div>

                                <!--Cargar Imagen-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="img">Imagen:</label>
                                    <input type="file"  id="img" data-target="#img" name="img" accept="image/*" >
                                </div>
                                <c:if test="${not empty error }"> 
                                <script>
    								// Get a reference to our file input
    								const fileInput = document.querySelector('input[type="file"]');

    
 									// Create a new File object
    								const myFile = new File([''], '${imgAltaSalida }', {
        								type: 'text/plain',
        								lastModified: new Date(),
    								});

    								// Now let's create a DataTransfer to get a FileList
    								const dataTransfer = new DataTransfer();
    								dataTransfer.items.add(myFile);
    								fileInput.files = dataTransfer.files;
								</script>
								</c:if>
                                
                            </form>
                            <br><br>
                            <button class="btn btn-primary btn-block" type="submit" form="alta" style="height: 47px; margin-top: -2px;">Confirmar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Booking End -->
    



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
        <script src="js/altaSalida.js"></script>
    
</body>

</html>
