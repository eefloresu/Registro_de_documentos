/*
 * Llamado principal de los componentes de Javascript, respecto a los usuarios.
 * @author eefloresu@unah.hn
 * @version 0.1.0 
*/

let nombreUser = document.querySelector("input#nombreUser");
let correoUser = document.querySelector("input#correoUser");
let registro = document.querySelector("input#registro"); 
let actions = new ActionUser(nombreUser, correoUser,registro);
let  sendButton = document.querySelector("input#sendButtonUser");
sendButton.addEventListener("click", actions.send.bind(actions));

function habilitarNombre() {
    let nombreUsuario = document.getElementById("divNombre");
    let registro = document.querySelector("input#registro"); 
    
    if (registro.checked) {
        nombreUsuario.style.display = "block";
    } else {
        nombreUsuario.style.display = "none";
    }
}

habilitarNombre()

/** 
* Codigo tomado de la documentacion de bootstratp
* abre el modal para iniciar sesion
 */ 
let modalUsuario = new bootstrap.Modal(document.getElementById('usuario'), {
  keyboard: false
})
modalUsuario.toggle()
//**********************************



