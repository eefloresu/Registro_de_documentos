/**
 *@author eefloresu@unah.hn
 *@Version 0.1.0
 *@date 2022-11-10
 */

class ActionUser{
	constructor(nombre, correo, registro){
		this.nombre = nombre;
		this.correo = correo;	
		this.registro = registro
	}
	
	send(){	
		
		let nombre = this.nombre.value;
		let correo = this.correo.value;
		let registro = this.registro.checked;
		let params = encodeURI( `nombre=${nombre}&correo=${correo}&registro=${registro}`);
		const xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = () => { // Call a function when the state changes.
		  	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
		   		const jsonResult = JSON.parse( xhr.responseText);
		   		console.log(jsonResult);
		   		const Exito = jsonResult.Exito
		   		if(Exito){
					let resultado = jsonResult.Resultado; 					
					let alerUsuario = document.getElementById('alerUsuario')
				    let wrapper = document.createElement('div')
				    wrapper.innerHTML = '<div class="alert alert-success alert-dismissible" role="alert">Usuario encontrado<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				
				    alerUsuario.append(wrapper)
					document.getElementById("UsuarioLogin").innerText=resultado;
					let correoDocumento = document.querySelector("input#correoDocumento");
					correoDocumento.value = correo
					modalUsuario.hide()
				}
				else
				{
					let alerUsuario = document.getElementById('alerUsuario')
				    let wrapper = document.createElement('div')
				    wrapper.innerHTML = '<div class="alert alert-danger alert-dismissible" role="alert">' + jsonResult.MensajeError + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>'
				
				    alerUsuario.append(wrapper)					
				}
		  	}
		}
		xhr.open("POST",encodeURIComponent(`serviceUser.jsp`))
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhr.send(params);
		
	}
	
	
}