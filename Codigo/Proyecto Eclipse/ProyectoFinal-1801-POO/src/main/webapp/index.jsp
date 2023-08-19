
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="eefloresu@unah.hn">
    <meta name="version" content="0.1.2">
    <meta name="date" content="2022-12-05">
    <title>Sistema de registros de documentos</title> 
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/estilos.css">
</head>
<body >
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">  
        <div class="container-fluid">
          <a class="navbar-brand" href="#">DOC Registry - <span id="UsuarioLogin"></span></a> 
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <!-- Inicio -->
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  Inicio 
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#agregar">Agregar/Extraer un Documento</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="#" onclick="limpiarModelo()">Limpiar Modelo de Datos</a></li>
                </ul>
              </li>
              <!-- Acerca de -->
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
                  Acerca de 
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#about">Autor</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
                  Iniciar Sesión
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#usuario">Iniciar Sesión</a></li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
    </nav>

      <div class="container">
        <div class="row mt-4">
          <div class="col-4">
          	
            <div id="totalDocumentos">  
              <p class="text-center text-light">Total de Documentos<span style="font-size: 60px;" id="CantidadTotalDocumentos"></span></p>
            </div>

            <div id="cantidadTipoDocumentos" class="mt-2">
              <p class="text-center text-light">Cantidad por tipo de documentos</p>  
              	<div id="docLibro">  
              		<p class="text-center text-dark"><a class="nav-link" href="#item-3" onclick="consultarTipoOEstado('1','-1');"><span id="cantidadLibros"></span>documentos de tipo libro</a></p>
            	</div> 
            	<div id="docRevista">  
              		<p class="text-center text-dark"><a class="nav-link" href="#item-1" onclick="consultarTipoOEstado('0','-1');"><span id="cantidadRevistas"></span>documentos de tipo revista</a></p>
            	</div>
            	<div id="docFotografia">
              		<p class="text-center text-dark"><a class="nav-link" href="#item-2"" onclick="consultarTipoOEstado('2','-1');"><span id="cantidadFotografias"></span>documentos de tipo fotografia</a></p>
            	</div>
            	<div id="docPapers">  
              		<p class="text-center text-dark"><a class="nav-link" href="#item-4"" onclick="consultarTipoOEstado('3','-1');"><span id="cantidadPapers"></span>documentos de tipo paper</a></p>
            	</div>
            	<div id="docTotalIngresados" class="mb-2">  
              		<p class="text-center text-dark"><a class="nav-link" href="#item-4"" onclick="consultarTipoOEstado('-1','0');"><span id="cantidadIngresados"></span>Total de documentos ingresados</a></p>
            	</div>
            	
            	<div id="docTotalExtraidos">  
              		<p class="text-center text-dark"><a class="nav-link" href="#item-4"" onclick="consultarTipoOEstado('-1','1');"><span id="cantidadRetirados"></span>Total de documentos extraidos</p></a>
            	</div>
            	
            </div>
          	
          </div>
          <div class="col-8">            
            <div id="listaDocumentos" class="overflow-auto">
              
            </div>
          </div> 
        </div>
        
      </div>
      
      <!--########################### Modales ####################################-->

	<div class="modal fade" id="DetalleDocumento" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Entrada de documento de tipo<span id="tituloDetalle"></span></h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       <div id="descripcionDetale"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-success" data-bs-dismiss="modal">Ok</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- Modal Acerca de-->
	<div class="modal fade" id="about" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Acerca de</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	       Sistema de registro de documentos.<br>
	       Este sistema ha sido creado por <b>Erlin Flores</b>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-success" data-bs-dismiss="modal">Ok</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal Iniciar Sesión-->
	<div class="modal fade" id="usuario" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel"><span>Iniciar Sesión</span></h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
		     <div id="alerUsuario"></div>
		     <label for="correoUser" class="col-form-label">Correo Electronico</label>
			   <input type="text" id="correoUser"  class="form-control"  >
			   <label for="correoUser" class="col-form-label">Registrar?</label>
			   <input type="checkbox" id="registro" onclick="habilitarNombre()"   > <br/>
			   <div id="divNombre">
			   <label for="nombreUser" class="col-form-label">Nombre</label>
			   <input type="text" id="nombreUser"  class="form-control"  >
		     </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
	      	<input type="button" class="btn btn-success"  id="sendButtonUser" value="Iniciar" >
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal Agregar / Extraer Documentos-->
	<div class="modal fade" id="agregar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="staticBackdropLabel">Agregar un nuevo registro de documento</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      <div id="alertaDocumento"></div>
	       <p>Ingrese la información digital para el registro del tipo de documento que está ingresando
	       	o retirando físicamente en el establecimiento. En el campo de información de registro 
	       	describa la circunstancia de la entrada o salida del documento.</p>
	       	
	       	<div id="divNombre">
			   <label for="correoDocumento" class="col-form-label">Responsable (email)</label>
			   <input type="text" id="correoDocumento"  class="form-control"  >
			   <label for="tipoDocumento" class="col-form-label">Documento</label>
			   <select id="tipoDocumento" name="Documento" class="form-control">
			   		<option value="0">Revista</option>
			   		<option value="1">Libro</option>
			   		<option value="2">Fotografía</option>
			   		<option value="3">Paper</option>
			   </select>
			   <label for="entradaRetiro" class="col-form-label">Entrada o retiro</label>
			   <select id="entradaRetiro" name="Entra o retiro" class="form-control">
			   		<option value="0" >Registrar</option>
			   		<option value="1">Retiro</option>
			   </select>
			   
			   <label for="informacion" class="col-form-label">Información del registro</label>
			   <textarea id="informacionRegistro" class="form-control"></textarea>
		     </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
	        <input id="sendButtonDocument" type="button" class="btn btn-success" value="Guardar">
	    </div>
	  </div>
	</div>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="assets/js/ActionUser.js"></script>
    <script src="assets/js/mainUser.js"></script>
    <script src="assets/js/ActionDocument.js"></script>
    <script src="assets/js/mainDocument.js"></script>

</body>
</html>