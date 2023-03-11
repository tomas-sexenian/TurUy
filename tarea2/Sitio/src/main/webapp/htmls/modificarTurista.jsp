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

    <!-- Includes -->
    <script src="js/main.js" defer></script>
    <script src="js/validate.js" defer></script></head>

<body>

<!-- Include navbar -->
<%@ include file="navbar.jsp"%>


    <!-- Header Start -->
    <div class="container-fluid page-header">
        <div class="container">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 400px">
                <h3 class="display-4 text-white text-uppercase">Modificar datos de turista</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Modificar datos de turista</p>
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
                            <form id="modificar" method="post" action="modificarUsuario" enctype="multipart/form-data">
                                <!-- Nickname-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lnickname">Nombre de usuario:</label>
                                    <input type="text" class="form-control px-4" id="nickname" name="nickname" style="height: 47px" value="${requestScope.nickname}" data-target="#nickname" required="required"  readonly/>
                                </div>

                                <!--Email-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lemail">Correo electrónico:</label>
                                    <input type="text" class="form-control px-4" id="email" name="correo" style="height: 47px" value="${requestScope.correo}" data-target="#email" required="required"  readonly/>
                                </div>


                                <!--Nombre-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lnombre">Nombre:</label>
                                    <input type="text" class="form-control px-4" id="nombre" name="nombre" style="height: 47px" value="${requestScope.nombre}" data-target="#nombre" required="required"  />
                                </div>

                                <!--Apellido-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lapellido">Apellido:</label>
                                    <input type="text" class="form-control px-4" id="apellido" name="apellido" style="height: 47px" value="${requestScope.apellido}" data-target="#apellido" required="required"  />
                                </div>

                                <!--Fecha de nacimiento-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="lfechaNac">Fecha de nacimiento:</label>
                                    <input type="date" class="form-control px-4" id="fechaNac" name="fecha" style="height: 47px" value="${requestScope.fecha}" data-target="#fechaNac" required="required"  />
                                </div>

                                <!--Link-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="llink">Nacionalidad:</label>
                                    <input type="text" class="form-control px-4" id="nacionalidad" name="nacionalidad" style="height: 47px" value="${requestScope.nacionalidad}" data-target="#link" required="required"  />
                                </div>
                                
                                <button type="button" class="btn btn-light m-lg-4 m-md-2 m-sm-1" id="cambiar" onclick="cambiarPass()">Cambiar contraseña</button>
                                <button type="button" class="btn btn-light m-lg-4 m-md-2 m-sm-1" id="cancelar" onclick="noCambiarPass()" style="display: none">No modificar contraseña</button>
                                
								<script>
								function cambiarPass() {
									document.getElementById('contenedorpassword').style.display='block';
									document.getElementById('confcontraseña').required=true;
									document.getElementById('contraseña').required=true;
									document.getElementById('contenedorConfirmar').style.display='block';
									document.getElementById('cambiar').style.display='none';
									document.getElementById('cancelar').style.display='block'
								}
								</script>
								
								<script>
								function noCambiarPass() {
									document.getElementById('contenedorpassword').style.display='none';
									document.getElementById('confcontraseña').required=false;
									document.getElementById('contraseña').required=false;
									document.getElementById('contenedorConfirmar').style.display='none';
									document.getElementById('cancelar').style.display='none';
									document.getElementById('cambiar').style.display='block'
								}
								</script>
								
								<!--Contraseña-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" id="contenedorpassword" style="display:none">
                                    <label for="lcontraseña">Nueva contraseña:</label>
                                    <input type="password" class="form-control px-4" name="contraseña" id="contraseña" onchange="validarPass()" style="height: 47px" />
                                </div>
                                
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" style="display:none" id="contenedorConfirmar">
                                    <label for="lcontraseña">Confirmar nueva contraseña:</label>
                                    <input type="password" class="form-control px-4" name="confcontraseña" id="confcontraseña" onchange="validarPass()" style="height: 47px" />
                                </div>



                                <!--Cargar Imagen-->
                                <div class="mb-3 mb-md-0 p-lg-4 p-md-2 p-sm-1" >
                                    <label for="img">Nueva imagen:</label>
                                    <input type="file"  id="img" data-target="#img" name="img" accept="image/*">
                                </div>

                            </form>
                            <br><br>
                            <button class="btn btn-primary btn-block" type="submit" form="modificar" style="height: 47px; margin-top: -2px;">Confirmar</button>
                            
                            
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


</body>

</html>
