package programLibraries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class GestionDocumento {
	private String nombreArchivo;
	private ModeloDatosDocumentos modeloDatos;

	public GestionDocumento(String nombreArchivo) {
		super();
		this.nombreArchivo = nombreArchivo;
		modeloDatos = new ModeloDatosDocumentos(nombreArchivo);
	}
	
	
	public Respuesta agregar(Documento documento) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = modeloDatos.agregar(documento);
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Respuesta extraer(int idDocumento) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = modeloDatos.extraer(idDocumento);
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Respuesta limpiar() {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = modeloDatos.limpiar();
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public void crearArchivoLimpiesa(Respuesta respuesta) {
		if(respuesta.isExito()) {
			modeloDatos.crearArchivoLimpiesa();
		}		
	}
	public Documento getDocumento(int id) {
		Documento documento = null;
		try {
			documento = modeloDatos.getDocumento(id);
		} catch (Exception e) {
			
		}
		
		return documento; 
	}
	
	public Documento getDocumento(int id,List<Documento> todosDocumentos) {
		Documento documento = null;
		try {
			documento = modeloDatos.getDocumento(id,todosDocumentos);
		} catch (Exception e) {
			
		}
		
		return documento; 
	}
	
	public List<Documento>  getDocumentosPorTipo(String tipo, List<Documento> todosDocumentos) {
		List<Documento> documentos = new ArrayList<>();
		
		try {
			int tipoFiltro = Integer.parseInt(tipo);
			eTipoDocumento tipoEnviado = eTipoDocumento.values()[tipoFiltro];
			documentos = modeloDatos.getDocumentosPorTipo(tipoEnviado, todosDocumentos);
		} catch (Exception e) {
			
		}
		
		return documentos;
	}
	
	public List<Documento>  getDocumentosPorEstado(String estado, List<Documento> todosDocumentos) {
		List<Documento> documentos = new ArrayList<>();
		
		try {
			int estadoFiltro = Integer.parseInt(estado);
			eEstado estadoEnviado = eEstado.values()[estadoFiltro];
			documentos = modeloDatos.getDocumentosPorEstado(estadoEnviado, todosDocumentos);
		} catch (Exception e) {
			
		}
		
		return documentos;
	}
	
	public List<Documento> getDocumentos(eTipoDocumento tipo) {
		List<Documento> documentos = new ArrayList<>();
		try {
			documentos = modeloDatos.getDocumentos(tipo); 
		} catch (Exception e) {
			
		}
		
		return documentos; 
	}
	
	public int getCantidadPorTipo(eTipoDocumento tipo, List<Documento> documentos) {
		int cantidad = 0;
		try {
			cantidad = modeloDatos.cantidadDocumentosPorTipo(tipo, documentos);
		} catch (Exception e) {
			
		}
		return cantidad;
	}
	
	public int getCantidadPorEstado(eEstado estado, List<Documento> documentos) {
		int cantidad = 0;
		try {
			cantidad = modeloDatos.getCantidadDocumentosPorEstado(estado, documentos);
		} catch (Exception e) {
			
		}
		return cantidad;
	}
}
