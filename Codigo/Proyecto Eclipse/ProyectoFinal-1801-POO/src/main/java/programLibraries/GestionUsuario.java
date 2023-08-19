package programLibraries;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class GestionUsuario {
	
	private String nombreArchivo;
	private ModeloDatosGestionUsuario modeloDatos; 
	
	public GestionUsuario(String nombreArchivo) {
		super();
		this.nombreArchivo = nombreArchivo;
		modeloDatos = new ModeloDatosGestionUsuario(nombreArchivo);
	}

	public Respuesta registrar(Usuario usuario) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = modeloDatos.registrar(usuario);
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Usuario autenticar(String correo) {
		Usuario respuesta = null;
		try {
			respuesta = modeloDatos.getUsuario(correo);
		} catch (Exception e) {
		}
		
		return respuesta; 
	}
	
	
}
