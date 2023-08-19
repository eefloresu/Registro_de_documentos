package programLibraries;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class Usuario {
	private int id;
	private String nombre;
	private String correo;
	
	public Usuario(int id, String nombre, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
	}
	
	public Usuario() {
		
	}
	
	public final int getId() {
		return id;
	}
	public final void setId(int id) {
		this.id = id;
	}
	public final String getNombre() {
		return nombre;
	}
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public final String getCorreo() {
		return correo;
	}
	public final void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		
		StringBuilder json = new StringBuilder("{");
		json.append(String.format("\"Id\": %s,", this.getId()));
		json.append(String.format("\"Nombre\": \"%s\",", this.getNombre()));
		json.append(String.format("\"Correo\": \"%s\"", this.getCorreo()));
		json.append("}");
		
		return json.toString().replaceAll("\\n+","");
	}
	
	
}
