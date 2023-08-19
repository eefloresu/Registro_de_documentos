package programLibraries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */

public class ModeloDatosGestionUsuario extends Validator {
	private String nombreArchivo;
	

	public ModeloDatosGestionUsuario(String nombreArchivo) {
		super();
		this.nombreArchivo = nombreArchivo;
		
	}
	
	public Respuesta registrar(Usuario usuario) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = validaVacios(usuario);
			if(respuesta.isExito()) {
				boolean correoValido = this.isEmail(usuario.getCorreo());  
				if(!correoValido)
				{
					respuesta.setMensaje("El formato del correo no es correcto");
					respuesta.setExito(false);
				}
				else {					
					// todo ok
					FileManager fmUsuario = new FileManager();
					String contenido = fmUsuario.read(this.nombreArchivo).getContent();
					
					List<Usuario> usuarios = getUsuarios();
					
					if(usuarios != null) {
						// validamos que no exista un usuario con el correo ingresado.
						respuesta = buscaUsuario(usuarios, usuario.getCorreo()); // isExito() sera true si usuario existe
						
						if(!respuesta.isExito()) { // si no existe el usuario se registrara
							// obtener un id identificador.
							int nuevoId = usuarios.size() + 1; // tamanio de la lista se le agrega uno
													
							String usuarioStr = String.format("%d;%s;%s\n",
									nuevoId,
									usuario.getNombre(),
									usuario.getCorreo()
									); // formatear valores de usuario
							
							
							String contenidoFinal = contenido ==null?"":contenido;
							
							fmUsuario.createAndOverride(this.nombreArchivo,contenidoFinal+ usuarioStr); // escribir contenido.
							respuesta.setExito(true);
							respuesta.setMensaje("Operacion exitosa");
						}							
					}
				}
			}
			
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Respuesta buscaUsuario(List<Usuario> usuarios,String correo) {
		Respuesta respuesta = new Respuesta(false, "Usuario no encontrado");
		try {
			for(Usuario usuario:usuarios) {
				if(usuario.getCorreo().toLowerCase().trim().equals(correo.trim().toLowerCase())) {
					respuesta.setExito(true);
					respuesta.setMensaje("Usuario encontrado");
					break;
				}
			}
		} catch (Exception e) {
			respuesta.setExito(false);
			respuesta.setMensaje("Error al buscar usuario");
		}
		
		return respuesta; 
	}
	
	public Usuario getUsuario(String correo) {
		Usuario retorno = null;
		try {
			List<Usuario> usuarios = getUsuarios();
			for(Usuario usuario:usuarios) {
				if(usuario.getCorreo().toLowerCase().trim().equals(correo.trim().toLowerCase())) {
					retorno = usuario;
					break;
				}
			}
		} catch (Exception e) {
			
		}
		
		return retorno; 
	}
	
	public Respuesta autenticar(String correo) {
		Respuesta respuesta = new Respuesta(false, "Usuario no encontrado");
		try {
			List<Usuario> usuarios = getUsuarios();
			for(Usuario usuario:usuarios) {
				if(usuario.getCorreo().toLowerCase().trim().equals(correo.trim().toLowerCase())) {
					respuesta.setExito(true);
					respuesta.setMensaje("Usuario encontrado");
					break;
				}
			}
		} catch (Exception e) {
			respuesta.setExito(false);
			respuesta.setMensaje("Error al iniciar sesion");
		}
		
		return respuesta; 
	}
	
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		try {
			FileManager fmUsuario = new FileManager();
			String contenido = fmUsuario.read(this.nombreArchivo).getContent();
			String[] valores = contenido.split("\n");
			for (String linea : valores) {
				if(!linea.equals("")) {
					Usuario usuario = new Usuario();
					
					String[] vlsUsuario = linea.split(";");
					int contador = 0;
					for (String valor : vlsUsuario) {
						
						if(contador == 1) {
							usuario.setNombre(valor);
						}
						else {
							if(contador == 0) {
								usuario.setId(Integer.parseInt(valor));
							}
							else {
								if(contador == 2) {
									usuario.setCorreo(valor);
								}
							}
						}	
						contador++;
					}
					usuarios.add(usuario);
				}
					
			}
			// establecer la lista de acuerdo al contenido...
		} catch (Exception e) {
			String error = e.getMessage();
		}
		
		return usuarios; 
	}

	@Override
	public Respuesta validaVacios(Object valor) {
		Respuesta respuesta = new Respuesta(true, "");
		try {
			Usuario usuario =(Usuario) valor;
			// Valida requeridos.
			if(usuario.getNombre().trim().equals("")) {
				respuesta.setMensaje("El campo nombre es requerido");
				respuesta.setExito(false);
			}
			if(usuario.getCorreo()==null) {
				respuesta.setMensaje("El campo correo es requerido");
				respuesta.setExito(false);
			}
			//*****************
			
		} catch (Exception e) {
			respuesta.setExito(false);
			respuesta.setMensaje("Error con los datos de usuario");
		}
		
		return respuesta; 
	}
}
