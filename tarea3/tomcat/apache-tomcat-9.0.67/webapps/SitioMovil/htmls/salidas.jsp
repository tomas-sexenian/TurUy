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
                <h3 class="display-4 text-white text-uppercase text-center">Salidas Turisticas</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                                   
                    
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Salidas</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


<c:set var="from" value="htmls/salidas.jsp" scope="session"  />
<c:set var = "actSelected" scope="request" value="${actividad}" />
<c:set var = "deptoSelected" scope="request" value="${departamento}" />
<c:set var = "catSelected" scope="request" value="${categoria}" />
<c:set var = "soloVigentes" scope="request" value="${soloVigentes}" />

<div class="container-fluid booking mt-5 pb-2">
        <div class="container pb-5">
            <div class="bg-light shadow" style="padding: 30px;">
                <div class="row align-items-center" style="min-height: 60px;">
                    <div class="col-md-3">
                        <form action="salidas" id ="depto">
                        <div class="row">
                        
                            <div class="col-md-12">
                                <div class="mb-3 mb-md-0">
                                    <select name="departamento" id="departamento" class="custom-select px-4" onchange = depto.submit() style="height: 47px;">
                                    	
                                    	<c:if test="${empty deptoSelected}">
                                    		<option value="" selected >Departamento</option>
                                    	</c:if>
                                    	<c:forEach items="${applicationScope.departamentos}" var="depto" varStatus="loop">
                                    		<option <c:if test="${deptoSelected eq depto}"> selected</c:if> value='${depto}'>${depto}</option>
                                    	</c:forEach>
                                    </select>
                                    <c:if test="${not empty categoria}">
										<input type="hidden" name="categoria" value="${categoria}" />
									</c:if>
                                    <c:if test="${soloVigentes eq 'on'}">
										<input type="hidden" name="soloVigentes" value="${sessionScope.soloVigentes}" />
									</c:if>
                                </div>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                    <div class="col-md-3">
                        <form action="salidas" id ="cat">
                        <div class="row">
                        
                            <div class="col-md-12">
                                <div class="mb-3 mb-md-0">
                                    <select name="categoria" id="categoria" class="custom-select px-4" onchange = cat.submit() style="height: 47px;">
                                    	<c:if test="${empty catSelected}">
                                    		<option value="" selected >Categoria</option>
                                    	</c:if>
                                    	<c:forEach items="${applicationScope.categorias}" var="categ" varStatus="loop">
                                    		<option <c:if test="${catSelected eq categ}"> selected</c:if> value='${categ}'>${categ}</option>
                                    	</c:forEach>
                                    </select>
                                    <c:if test="${not empty deptoSelected}">
										<input type="hidden" name="departamento" value="${departamento}" />
									</c:if>
									<c:if test="${soloVigentes eq 'on'}">
										<input type="hidden" name="soloVigentes" value="${soloVigentes}" />
									</c:if>
                                </div>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                    
                    <div class="col-md-4">
                        <form action="salidas" id ="act">
                        <div class="row">
                        
                            <div class="col-md-12">
                                <div class="mb-3 mb-md-0">
                                    <select name="actividad" id="actividad" class="custom-select px-4" onchange = act.submit() style="height: 47px;">
                                    	
                                    	<c:if test="${empty actSelected}">
                                    		<option value="" selected>Actividad</option>
                                    	</c:if>
                                       	
                                    	<c:forEach items="${sessionScope.actividades}" var="act" varStatus="loop">
                                    		<option <c:if test="${actSelected eq act}"> selected</c:if> value='${act}'>${act}</option>
                                    	</c:forEach>
                                    </select>
                                    <c:if test="${not empty deptoSelected}">
										<input type="hidden" name="departamento" value="${departamento}" />
									</c:if>
									<c:if test="${not empty catSelected}">
										<input type="hidden" name="categoria" value="${categoria}" />
									</c:if>
									<c:if test="${soloVigentes eq 'on'}">
										<input type="hidden" name="soloVigentes" value="${soloVigentes}" />
									</c:if>
                                </div>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary btn-block" onclick="location.href = 'salidas'" style="height: 47px;">Limpiar</button>
                    </div>
                    
                </div >
                <div class="mt-2">
                
                <form action="salidas" id="vigentes">
                	<input type="checkbox" id="soloVigentes" name="soloVigentes" onchange=vigentes.submit() <c:if test="${sessionScope.soloVigentes eq 'on'}">checked</c:if>>
					<label for="soloVigentes"> Solo mostrar salidas vigentes</label><br>
                    <c:if test="${not empty deptoSelected}">
						<input type="hidden" name="departamento" value="${departamento}" />
					</c:if>
					<c:if test="${not empty catSelected}">
						<input type="hidden" name="categoria" value="${categoria}" />
					</c:if>
					<c:if test="${not empty actSelected}">
						<input type="hidden" name="actividad" value="${actividad}" />
					</c:if>
				</form>
				</div>
            </div>
        </div>
</div>
    
    
<!-- Salidas Start -->

<div class="container-fluid py-2">
    <div class="container pt-5 pb-3">
        <div class="text-center mb-3 pb-3">
            <h6 class="text-primary text-uppercase" style="letter-spacing: 5px;">Salidas</h6>
            <h1>Salidas Turisticas</h1>
        </div>
        <div class="row">
            <c:forEach items="${sessionScope.salidas}" var="sal" varStatus="loop">
        
            	<div class="col-lg-4 col-md-4 mb-4">
                	<div class="package-item bg-white mb-2">
                	
                    	<c:set var = "img" scope="session" value="${sal.imgSrc}" />
						<c:if test="${empty img }">
							<c:set var = "img" scope="session" value="${initParam.defaultImgAct}" />
						</c:if>
                    	<img class="img-fluid" src="Imagenes?id=${img}" alt="" style="height: 230px; width: 90%; margin: 5%; object-fit: cover;">
                    	<div class="p-4">
                        	<div class="d-flex justify-content-between mb-3">
                            	<small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${sal.fechaSalida}</small>
                            	<small class="m-0"><i class="fa fa-clock-o text-primary mr-2"></i>${sal.horaSalida} hs</small>
                        	</div>
                        	<a class="h5 text-decoration-none" href="consultaSalida?salida=${sal.nombre}">${sal.nombre}</a>
                        	<div class="border-top mt-4 pt-4">
                            	<div class="d-flex justify-content-between" style="padding: 5px">
                                	<small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${sal.lugarSalida}</small>
                            	</div>
                        	</div>
                        	<div class="col-md-17">
                            	<p>
                                	<button class="btn btn-primary btn-block" onclick="location.href='consultaSalida?salida=${sal.nombre}'" type="submit" style="padding-top: 2px; margin-top: 20px;">Mas informacion</button>
                            	</p>
                        	</div>
                    	</div>
                	</div>
            	</div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- Salidas End -->


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