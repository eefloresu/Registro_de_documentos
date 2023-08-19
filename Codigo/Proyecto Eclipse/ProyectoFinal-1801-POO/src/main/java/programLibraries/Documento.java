package programLibraries;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class Documento {
	private int id;
	private String descripcion;
	private Usuario responsable;
	private eTipoDocumento tipo;
	private eEstado estado;
	
	public Documento() {}
	
	public Documento(int id, String descripcion, Usuario responsable, eTipoDocumento tipo, eEstado estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.responsable = responsable;
		this.tipo = tipo;
		this.estado = estado;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descripcion
	 */
	public final String getDescripcion() {
		return descripcion;
	}



	/**
	 * @param descripcion the descripcion to set
	 */
	public final void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the responsable
	 */
	public final Usuario getResponsable() {
		return responsable;
	}



	/**
	 * @param responsable the responsable to set
	 */
	public final void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	/**
	 * @return the tipo
	 */
	public final eTipoDocumento getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public final void setTipo(eTipoDocumento tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the estado
	 */
	public final eEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public final void setEstado(eEstado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", descripcion=" + descripcion + ", responsable=" + responsable + ", tipo="
				+ tipo + ", estado=" + estado + ", getId()=" + getId() + ", getDescripcion()=" + getDescripcion()
				+ ", getResponsable()=" + getResponsable() + ", getTipo()=" + getTipo() + ", getEstado()=" + getEstado()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
		
}
