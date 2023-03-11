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
	<link href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.default.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <!-- Includes -->
</head>

<body>

<!-- Include navbar -->
<%@ include file="navbar.jsp"%>



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

<c:set var = "error" scope="request" value="${error}" />

    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Alta de Actividad Turistica</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Alta de Actividad Turistica</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->

    <!-- Formulario Start -->
    <div class="container-fluid booking mt-5 pb-5">
        <div class="container pb-5">
            <div class="bg-light shadow" style="padding: 30px;">
                <div class="row align-items-center" style="min-height: 60px; justify-content: center;">
                    <div class="col-md-10" style="justify-content: center;">
                        <div class="row;text-align:center;">
                        
                            <form action="altaActividad" id ="alta" method="post" enctype="multipart/form-data">
                            
                                <!-- Departamentos-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="fdept">Departamento:</label>
                                    <select class="custom-select px-4" style="height: 47px;" id="fdept" name="fdept" id="fdept" required>
                                    	<c:if test="${not empty error }">
                                    		<option value="${departamentoAltaAct }"  selected>${departamentoAltaAct }</option>
                                    	</c:if>
                                    	<c:if test="${empty error }">
                                    		<option value="" disabled selected hidden="true">Seleccionar...</option>
                                    	</c:if>
                                    	
                                    	<c:forEach items="${applicationScope.departamentos}" var="depto" varStatus="loop">
                                    		<option value='${depto}'>${depto}</option>
                                    	</c:forEach>
                                    </select>
                                </div>

                                <!--Nombre-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="nombre">Nombre de la actividad:</label>
                                    <input type="text" class="form-control px-4" id="nombre" name="nombre" style="height: 47px"  data-target="#nombre" required="required"  />
                                </div>


                                <!--Ciudad-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="ciudad">Ciudad:</label>
                                    <input type="text" class="form-control px-4" id="ciudad" name="ciudad" style="height: 47px" <c:if test="${not empty error }"> value="${ciudadAltaAct }"</c:if> data-target="#ciudad" required="required"  />
                                </div>

                                <!--Duracion-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="duracion">Duracion:</label>
                                    <input type="number" class="form-control px-2" id="duracion" name="duracion" min="1" style="height: 47px" <c:if test="${not empty error }"> value="${duracionAltaAct }"</c:if> data-target="#duracion" required="required"/>
                                </div>

                                <!--Descripcion-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="descripcion">Descripcion:</label>
                                    <textarea id="descripcion" name="descripcion" class="form-control px-4" rows="4" cols="50" > <c:if test="${not empty error }">${descripcionAltaAct }</c:if> </textarea>
                                </div>

                                <!--Costo-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="costo">Costo:</label>
                                    <input type="number" class="form-control px-2" id="costo" name="costo" min="1" style="height: 47px" <c:if test="${not empty error }"> value="${costoAltaAct }"</c:if> data-target="#costo" required="required"/>
                                </div>

                                <!-- Categorias-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" style=" display: block;">
                                    <label for="categorias">Categorias:</label>
                                    <select name="categoriasMultiple" id="categoriasMultiple" multiple="multiple" required>
                                    
                                    	<c:if test="${not empty error }">
        	                            	<c:forEach items="${categoriasAltaAct}" var="catt" varStatus="loop">
    	                                		<option value='${catt}' selected>${catt}</option>
	                                    	</c:forEach>
                                    	</c:if>
                                    	<c:if test="${empty error }">
                                    		<option value="" disabled selected hidden="true">Seleccionar...</option>
                                    	</c:if>
                                    	<c:forEach items="${applicationScope.categorias}" var="categoria" varStatus="loop">
                                    		<option value='${categoria}'>${categoria}</option>
                                    	</c:forEach>
                                    </select>
                                </div>

                                <!--Cargar Imagen-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="img">Imagen:</label>
                                    <input type="file"  id="img" data-target="#img" name="img" accept="image/*">
                                </div>
                                <c:if test="${not empty error }"> 
                                <script>
    								// Get a reference to our file input
    								const fileInput = document.querySelector('input[type="file"]');

    
 									// Create a new File object
    								const myFile = new File([''], '${imgAltaAct }', {
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
                            <button class="btn btn-primary btn-block" form="alta" type="submit" style="height: 47px;">Confirmar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--  Formulario End -->

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
