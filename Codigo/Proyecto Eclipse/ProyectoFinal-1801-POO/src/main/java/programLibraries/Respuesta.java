package programLibraries;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class Respuesta {
	private boolean exito;
	private String mensaje;	
	
	
	public Respuesta(boolean exito, String mensaje) {
		super();
		this.exito = exito;
		this.mensaje = mensaje;
	}
	
	 
	public final boolean isExito() {
		return exito;
	}
	public final void setExito(boolean exito) {
		this.exito = exito;
	}
	public final String getMensaje() {
		return mensaje;
	}
	public final void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Exito\": %s,", this.exito));
		json.append(String.format("\"MensajeError\": \"%s\"", this.getMensaje()));
		json.append("}");
		
		return json.toString().replaceAll("\\n+","");
	}
	
}
