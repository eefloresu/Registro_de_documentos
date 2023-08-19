<%@page import="programLibraries.Respuesta"%>
<%@page import="programLibraries.Usuario"%>
<%@page import="programLibraries.GestionUsuario"%>
<%@page import="programLibraries.GestionDocumento"%>
<%
/**
* @author eeflores@unah.hn
* @version 0.1.0
* @date 2022-12-04 
* */
GestionUsuario gestionUsuarios = new GestionUsuario("eclipse/Folder/UsersModel.csv");

String nombre = request.getParameter("nombre");
String correo = request.getParameter("correo");
String registro = request.getParameter("registro");
Respuesta respuesta = new Respuesta(false,"");
if(!registro.equals("true")){ // busca el usuario en el modelo.
	Usuario usuario = gestionUsuarios.autenticar(correo);
	if(usuario == null){
		respuesta.setExito(false);
		if(correo.trim().equals("")){
			respuesta.setMensaje("Campo correo es requerido.");			
		}
		else{
			respuesta.setMensaje("Usuario no encontrado.");
		}
		
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Exito\": %s,", respuesta.isExito()));
		json.append(String.format("\"MensajeError\": \"%s\",", respuesta.getMensaje()));
		json.append(String.format("\"Resultado\": \"%s\"", ""));
		json.append("}");
		
		out.print(json.toString().replaceAll("\\n+",""));
	}
	else{
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Exito\": %s,", true));
		json.append(String.format("\"MensajeError\": \"%s\",", respuesta.getMensaje()));
		json.append(String.format("\"Resultado\": \"%s\"", usuario.getNombre()));
		json.append("}");
		
		out.print(json.toString().replaceAll("\\n+",""));
	}
}
else{
	Usuario usuario = new Usuario(0,nombre, correo);
	respuesta = gestionUsuarios.registrar(usuario);
	
	StringBuilder json = new StringBuilder("{");
	json.append(String.format("\"Exito\": %s,", respuesta.isExito()));
	json.append(String.format("\"MensajeError\": \"%s\",", respuesta.getMensaje()));
	json.append(String.format("\"Resultado\": \"%s\"", nombre));
	json.append("}");
	
	out.print(json.toString().replaceAll("\\n+",""));
}



%>