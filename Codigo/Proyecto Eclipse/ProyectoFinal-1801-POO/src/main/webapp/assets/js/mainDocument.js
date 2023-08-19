/*
 * Llamado principal de los componentes de Javascript, respecto a los documentos.
 * @author eefloresu@unah.hn
 * @version 0.1.0 
*/

let correoDocumento = document.querySelector("input#correoDocumento");
let tipoDocumento = document.getElementById("tipoDocumento");
let entradaRetiro = document.getElementById("entradaRetiro");
let informacionRegistro = document.querySelector("textarea#informacionRegistro"); 
let actionsDocuments = new ActionDocument(correoDocumento, tipoDocumento,entradaRetiro,informacionRegistro);
let sendButtonDocuments = document.querySelector("input#sendButtonDocument");
sendButtonDocuments.addEventListener("click", actionsDocuments.send.bind(actionsDocuments));

// *********** llenar listas ******************************
function mostrarListas(){
	let params = encodeURI( `action=listar`);
	const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
		   		const jsonResult = JSON.parse( xhr.responseText);
		   		
		   		const Exito = jsonResult.Exito
		   		if(Exito){
					let CantidadTotalDocumentos = document.getElementById('CantidadTotalDocumentos')
					let cantidadLibros = document.getElementById('cantidadLibros')
					let cantidadRevistas = document.getElementById('cantidadRevistas')
					let cantidadFotografias = document.getElementById('cantidadFotografias')
					let cantidadPapers = document.getElementById('cantidadPapers')
					let cantidadIngresados = document.getElementById('cantidadIngresados')
					let cantidadRetirados = document.getElementById('cantidadRetirados')
					
					CantidadTotalDocumentos.innerHTML =' <br/>'+jsonResult.totalDocumentos;
					cantidadLibros.innerHTML =jsonResult.cantidadLibros + ' ';
					cantidadRevistas.innerHTML =jsonResult.cantidadRevistas + ' '
					cantidadFotografias.innerHTML =jsonResult.cantidadFotografias + ' '
					cantidadPapers.innerHTML =jsonResult.cantidadPapers + ' '
					cantidadIngresados.innerHTML =jsonResult.cantidadIngresados + ' '
					cantidadRetirados.innerHTML =jsonResult.cantidadRetirados + ' '
				}
				else
				{
					let alertaDocumento = document.getElementById('alertaDocumento')
				    let wrapper = document.createElement('div')
				    wrapper.innerHTML = '<div class="alert alert-danger alert-dismissible" role="alert">' + jsonResult.MensajeError + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				
				    alertaDocumento.append(wrapper)					
				}
		  	}
		}
		xhr.open("POST",encodeURIComponent(`serviceDocuments.jsp`)) 
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhr.send(params);
}

mostrarListas(); // muestra el menu secundario con las cantidades correspondientes.


//*********************************************************

//***************Consultando por tipo o estado*************
function consultarTipoOEstado(tipoF, estadoF){
	let params = encodeURI( `tipo=${tipoF}&estado=${estadoF}&action=consultaLista`);
	const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				const jsonResult = JSON.parse( xhr.responseText);
				let listaDocumentos = document.getElementById('listaDocumentos')
				let html = `Documentos registrados <hr><ol class="list-group list-group-numbered" >`
		   		for(let i=0;i<jsonResult.length;i++){
					const documento = jsonResult[i];
					 html += `
						 <li class="list-group-item d-flex justify-content-between align-items-start">
		                  <div class="ms-2 me-auto">
		                    <div class="fw-bold">- Documento: ${documento.Tipo}</div> 
		                    Responsable: ${documento.Responsable}
		                  </div>
		                  <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#DetalleDocumento" onClick="consultarDocumento(${documento.idDocumento})">Ver descripcion</button>
		                </li>
					`
				}
		   		html += `</ol>`
		   		listaDocumentos.innerHTML = html
		  	}
		}
		xhr.open("POST",encodeURIComponent(`serviceDocuments.jsp`)) 
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhr.send(params);
}
consultarTipoOEstado("4","-1") // funcion que muestra todos los documentos en el modelo de datos.
//*********************************************************

// funcion para obtener un documento y mostrar el detalle
function consultarDocumento(idDocumento){
	let params = encodeURI( `id=${idDocumento}&action=consultaDocumento`);
	const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				const documento = JSON.parse( xhr.responseText);
				let tituloDetalle = document.getElementById('tituloDetalle')	
				let descripcionDetale = document.getElementById("descripcionDetale")			
		   		tituloDetalle.innerHTML = ` ${documento.Tipo} #${documento.idDocumento}: ${documento.Responsable}`
		   		descripcionDetale.innerHTML = documento.Descripcion
		  	}
		}
		xhr.open("POST",encodeURIComponent(`serviceDocuments.jsp`)) 
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhr.send(params);
}
//*******************************************************


// funcion para limpiar el modelo de datos de documentos.
function limpiarModelo(){
	let params = encodeURI( `action=limpiar`);
	const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				const jsonResult = JSON.parse( xhr.responseText);		   		
		   		const Exito = jsonResult.Exito
		   		if(Exito){
					 mostrarListas();
				     consultarTipoOEstado("4","-1")
				}			
		  	}
		}
		xhr.open("POST",encodeURIComponent(`serviceDocuments.jsp`)) 
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhr.send(params);
}
//*******************************************************

