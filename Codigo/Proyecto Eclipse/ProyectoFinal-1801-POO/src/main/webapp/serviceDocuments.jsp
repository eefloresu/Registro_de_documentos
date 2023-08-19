<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="programLibraries.eTipoDocumento"%>
<%@page import="programLibraries.eEstado"%>
<%@page import="programLibraries.Documento"%>
<%@page import="programLibraries.Respuesta"%>
<%@page import="programLibraries.Usuario"%>
<%@page import="programLibraries.GestionDocumento"%>
<%@page import="programLibraries.GestionUsuario"%>
<%
/**
* @author eeflores@unah.hn
* @version 0.1.0
* @date 2022-12-04 
* */
GestionDocumento gestion = new GestionDocumento("eclipse/Folder/DocumentsModel.csv");
GestionUsuario gestionUsuarios = new GestionUsuario("eclipse/Folder/UsersModel.csv");


String correo = request.getParameter("correo");
String tipoDocumento = request.getParameter("tipoDocumento");
String entradaRetiro = request.getParameter("entradaRetiro");
String informacionRegistro = request.getParameter("informacionRegistro");
String action = request.getParameter("action");

Respuesta respuesta = new Respuesta(false,"");

if(action.equals("registrar")){
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
		int estado = Integer.parseInt(entradaRetiro);
		int tipo = Integer.parseInt(tipoDocumento);
	
		Documento documento = new Documento();
		documento.setId(0);
		documento.setDescripcion(informacionRegistro);
		documento.setEstado(eEstado.values()[estado]);
		documento.setResponsable(usuario);
		documento.setTipo(eTipoDocumento.values()[tipo]);
		respuesta = gestion.agregar(documento);
	
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Exito\": %s,", respuesta.isExito()));
		json.append(String.format("\"MensajeError\": \"%s\",", respuesta.getMensaje()));
		json.append(String.format("\"Resultado\": \"%s\"", ""));
		json.append("}");
	
		out.print(json.toString().replaceAll("\\n+",""));
	}
}
else{
	List<Documento> documentos = gestion.getDocumentos(eTipoDocumento.Todos);
	if(action.equals("listar")){		
		int cantidadRevistas = gestion.getCantidadPorTipo(eTipoDocumento.Revista, documentos);
		int cantidadLibros = gestion.getCantidadPorTipo(eTipoDocumento.Libro, documentos);
		int cantidadFotografias = gestion.getCantidadPorTipo(eTipoDocumento.Fotografia, documentos);
		int cantidadPapers = gestion.getCantidadPorTipo(eTipoDocumento.Paper, documentos);
		int totalDocumentos = documentos.size(); 
		int cantidadIngresados = gestion.getCantidadPorEstado(eEstado.Registrado, documentos);
		int cantidadRetirados = gestion.getCantidadPorEstado(eEstado.Retirado, documentos);
		
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Exito\": %s,", true));
		json.append(String.format("\"MensajeError\": \"%s\",", ""));
		json.append(String.format("\"cantidadRevistas\": %s,", cantidadRevistas));
		json.append(String.format("\"cantidadLibros\": %s,", cantidadLibros));
		json.append(String.format("\"cantidadFotografias\": %s,", cantidadFotografias));
		json.append(String.format("\"cantidadPapers\": %s,", cantidadPapers));
		json.append(String.format("\"totalDocumentos\": %s,", totalDocumentos));
		json.append(String.format("\"cantidadIngresados\": %s,", cantidadIngresados));
		json.append(String.format("\"cantidadRetirados\": %s", cantidadRetirados));
		json.append("}");

		out.print(json.toString().replaceAll("\\n+",""));
	}
	else{
		if(action.equals("consultaDocumento")){
			String idDocumentoStr = request.getParameter("id");
			int idDocumento = Integer.parseInt(idDocumentoStr);
			Documento documento = gestion.getDocumento(idDocumento,documentos);
			StringBuilder json = new StringBuilder("{");
			json.append(String.format("\"idDocumento\": %s,", documento.getId()));
			json.append(String.format("\"Responsable\": \"%s\",", documento.getResponsable().getCorreo()));
			json.append(String.format("\"Tipo\": \"%s\",", documento.getTipo().name()));
			json.append(String.format("\"Descripcion\": \"%s\"", documento.getDescripcion()));
			json.append("}");
			out.print(json.toString().replaceAll("\\n+",""));
		}
		else{
			
			if(action.equals("limpiar")){
				respuesta = gestion.limpiar();
				StringBuilder json = new StringBuilder("{");
				json.append(String.format("\"Exito\": %s,", respuesta.isExito()));
				json.append(String.format("\"MensajeError\": \"%s\",", respuesta.getMensaje()));
				json.append(String.format("\"Resultado\": \"%s\"", ""));
				json.append("}");				
				out.print(json.toString().replaceAll("\\n+",""));
			}
			else{
				String tipoDoc = request.getParameter("tipo");
				String estadoDocumento = request.getParameter("estado");	
				List<Documento>  documentosConsulta = new ArrayList<>();
				StringBuilder json = new StringBuilder("[");
				
				if(tipoDoc.equals("-1")){
					// consultando por estado.			
					documentosConsulta = gestion.getDocumentosPorEstado(estadoDocumento, documentos);
				}else{
					// consultando por tipo
					documentosConsulta = gestion.getDocumentosPorTipo(tipoDoc, documentos);
				}
				
				for(Documento doc:documentosConsulta){
					if(json.toString().equals("["))
						json.append("{");
					else
						json.append(",{");
					json.append(String.format("\"idDocumento\": %s,", doc.getId()));
					json.append(String.format("\"Responsable\": \"%s\",", doc.getResponsable().getCorreo()));
					json.append(String.format("\"Tipo\": \"%s\"", doc.getTipo().name()));
					json.append("}");
				}
				
				json.append("]");
				out.print(json.toString().replaceAll("\\n+",""));
			}				
		}
			
	}
	
}
%>