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
    <link href="css/pestanas.css" rel="stylesheet">
    <!-- Includes -->
    <script src="js/main.js" defer></script>
    <script src="js/validate.js" defer></script>
    
</head>

<body>

<!-- Include navbar -->
<%@ include file="navbar.jsp"%>


    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Alta de Usuario</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Alta de Usuario</p>
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
                            <form id="alta" method="post" action="altaUsuario" enctype="multipart/form-data">
								
								<h4>Complete los datos</h4>


                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="nickname">Nickname:</label>
                                    <input type="text" class="form-control px-2" id="nickname" name="nickname" min="0" style="height: 47px" placeholder="" data-target="#nick" required="required" <c:if test="${not empty error }"> value="${nicknameIngresado }"</c:if>/>
                                	<span></span>
                                </div>

                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" class="form-control px-2" id="nombre" name="nombre" min="0" style="height: 47px" placeholder="" data-target="#nombre" required="required" <c:if test="${not empty error }"> value="${nombreIngresado }"</c:if>/>
                                </div>

                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="apellido">Apellido:</label>
                                    <input type="text" class="form-control px-2" id="apellido" name="apellido" min="0" style="height: 47px" placeholder="" data-target="#apellido" required="required" <c:if test="${not empty error }"> value="${apellidoIngresado }"</c:if>/>
                                </div>

                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="pass">Contraseña:</label>
                                    <input type="password" class="form-control px-2" id="contraseña" name="contraseña" style="height: 47px" onchange="validarPass()"  required="required" <c:if test="${not empty error }"> value="${contraseñaIngresada }"</c:if>/>
                                </div>

                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="confcontraseña">Confirme la contraseña:</label>
                                    <input type="password" class="form-control px-2" id="confcontraseña" name="confcontraseña" style="height: 47px" onchange="validarPass()" required="required" <c:if test="${not empty error }"> value="${confContraseñaIngresada }"</c:if>/>
                                </div>

                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="correo">Correo electrónico:</label>
                                    <input type="email" class="form-control px-2" id="correo" name="correo" style="height: 47px" placeholder="" data-target="#correo" required="required" <c:if test="${not empty error }"> value="${correoIngresado }"</c:if>/>
                                	<span></span>
                                </div>
                                
                                <!--Fecha-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="fecha">Fecha de Nacimiento:</label>
                                    <input type="date" class="form-control px-2" id="fecha" name="fecha" style="height: 47px" placeholder="" data-target="#fNac" required="required" <c:if test="${not empty error }"> value="${fechaIngresado }"</c:if>/>
                                </div>

                                <!--Cargar Imagen-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="img">Imagen:</label>
                                    <input type="file"  id="img" data-target="#img" name="img" accept="image/*" <c:if test="${not empty error }"> value="${cantMax }"</c:if>/>
                                </div>




                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <input type="radio" name="tipo" id="cbox1" <c:if test="${empty tipoIngresado || tipoIngresado eq 'turista'}">checked='checked'</c:if> value="turista" onclick="document.getElementById('contnac').style.display='block'; document.getElementById('contdesc').style.display='none'; document.getElementById('contlink').style.display='none'; document.getElementById('nacionalidad').setAttribute('required', 'required'); document.getElementById('descripcion').removeAttribute('required');">
                                    <label for="cbox1">Turista</label>
                                    <input type="radio" name="tipo" id="cbox2" <c:if test="${tipoIngresado eq 'proveedor'}">checked='checked'</c:if> value="proveedor" onclick="document.getElementById('contdesc').style.display='block'; document.getElementById('contlink').style.display='block';document.getElementById('contnac').style.display='none'; document.getElementById('descripcion').setAttribute('required', 'required'); document.getElementById('nacionalidad').removeAttribute('required');">
                                    <label for="cbox2">Proveedor</label>
                                </div>

                                <!--DescripciÃ³n-->
                                
	                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" id="contdesc" style=<c:if test="${tipoIngresado eq 'proveedor'}">'display: block'</c:if><c:if test="${tipoIngresado eq 'turista' || empty tipoIngresado}">'display: none'</c:if>>
	                                    <label for="ldescripcion">Descripción:</label>
	                                    <textarea id="descripcion" name="descripcion" class="form-control px-4" rows="4" cols="50" <c:if test="${tipoIngresado eq 'proveedor'}">required</c:if> <c:if test="${not empty error }"> value="${descripcionIngresado }"</c:if>></textarea>
	                                </div>
	
	                                 <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" id="contlink" style=<c:if test="${tipoIngresado eq 'proveedor'}">'display: block'</c:if><c:if test="${tipoIngresado eq 'turista' || empty tipoIngresado}">'display: none'</c:if>>
	                                    <label for="link">Link:</label>
	                                    <input type="text" class="form-control px-2" id="link" name="link" min="0" style="height: 47px" placeholder="" data-target="link" <c:if test="${tipoIngresado eq 'proveedor'}">required</c:if> <c:if test="${not empty error }"> value="${linkIngresado }"</c:if>/>
	                                </div>
								
	                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" id="contnac" style=<c:if test="${tipoIngresado eq 'turista' || empty tipoIngresado}">'display: block'</c:if><c:if test="${tipoIngresado eq 'proveedor'}">'display: none'</c:if>>
	                                    <label for="nacionalidad">Nacionalidad:</label>
	                                    <input type="text" class="form-control px-2" id="nacionalidad" name="nacionalidad" min="0" style="height: 47px" placeholder="" data-target="#nacionalidad" <c:if test="${tipoIngresado eq 'turista' || empty tipoIngresado}">required</c:if> <c:if test="${not empty error }"> value="${nacionalidadIngresado }"</c:if>/>
	                                </div>
	                                
                            </form>
                            <br>
                            <button class="mb-3 mb-md-0 btn btn-primary btn-block p-2" type="submit" form ="alta" style="height: 47px">Confirmar</button>
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
    <script src="js/script.js"></script>
    <script src="js/script2.js"></script>
    

</body>

</html>
