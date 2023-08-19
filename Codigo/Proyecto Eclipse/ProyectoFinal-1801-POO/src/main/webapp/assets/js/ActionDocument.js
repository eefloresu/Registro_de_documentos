/**
 *@author eefloresu@unah.hn
 *@Version 0.1.0
 *@date 2022-11-10
 */

class ActionDocument{
	constructor(correo, tipoDocumento,entradaRetiro,informacionRegistro){
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;	
		this.entradaRetiro = entradaRetiro;
		this.informacionRegistro = informacionRegistro;
	}
	
	send(){	
		
		let correo = this.correo.value;
		let tipoDocumento = this.tipoDocumento.value;
		let entradaRetiro = this.entradaRetiro.value;
		let informacionRegistro = this.informacionRegistro.value;
		let params = encodeURI( `correo=${correo}&tipoDocumento=${tipoDocumento}&entradaRetiro=${entradaRetiro}&informacionRegistro=${informacionRegistro}&action=registrar`);
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				const jsonResult = JSON.parse( xhr.responseText);
		   		
		   		const Exito = jsonResult.Exito
		   		if(Exito){
					let resultado = jsonResult.Resultado; 					
					let alertaDocumento = document.getElementById('alertaDocumento')
				    let wrapper = document.createElement('div')
				    wrapper.innerHTML = '<div class="alert alert-success alert-dismissible" role="alert">Documento registrado<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				
				    alertaDocumento.append(wrapper)
				    mostrarListas();
				    consultarTipoOEstado("4","-1") // funcion que muestra todos los documentos en el modelo de datos.
				    let tipoDocumento = document.getElementById("tipoDocumento");
					let entradaRetiro = document.getElementById("entradaRetiro");
					let informacionRegistro = document.querySelector("textarea#informacionRegistro"); 
					tipoDocumento.value =0;
					entradaRetiro.value =0;
					informacionRegistro.value ="";
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
	
	
}