package programLibraries;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eefloresu@unah.hn
 * @version 0.1.0
 * */
public class ModeloDatosDocumentos extends Validator  {
	
	private List<Documento> documentos;
	private String nombreArchivo;
	
	public ModeloDatosDocumentos(String nombreArchivo) {
		super();
		this.documentos = new ArrayList<Documento>();
		this.nombreArchivo = nombreArchivo;
	}

	public Respuesta agregar(Documento documento) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			respuesta = validaVacios(documento);
			if(respuesta.isExito()) {
				boolean correoValido = this.isEmail(documento.getResponsable().getCorreo());
				if(!correoValido)
				{
					respuesta.setMensaje("El formato del correo no es correcto");
					respuesta.setExito(false);
				}
				else {
					// todo ok
					FileManager fmDocumento = new FileManager();
					String contenido = fmDocumento.read(this.nombreArchivo).getContent();
					
					List<Documento> documentos = getDocumentos(eTipoDocumento.Todos);
					
					if(documentos != null) {
						// obtener un id identificador.
						int nuevoId = documentos.size() + 1; // Si es 0 el tamanio de la lista se le agrega uno
												
						String documentoStr = String.format("%d;%s;%d;%s;%d\n",
								nuevoId,
								documento.getDescripcion(),
								documento.getTipo().ordinal(),
								documento.getResponsable().getCorreo(),
								documento.getEstado().ordinal()
								); // formatear valores de usuario
						
						
						String contenidoFinal = contenido ==null?"":contenido;
						
						fmDocumento.createAndOverride(this.nombreArchivo,contenidoFinal+ documentoStr); // escribir contenido.
						respuesta.setExito(true);
					}
				}
			}
			
		} catch (Exception e) {
			respuesta.setExito(false);
			respuesta.setMensaje("Error al agregar documento");
		}
		
		return respuesta; 
	}
	
	public Respuesta extraer(int idDocumento) {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			Documento documento = null;
			
			List<Documento> documentos = getDocumentos(eTipoDocumento.Todos);
			int indice = 0;
			for( Documento doc : documentos ) {
				if(doc.getId() == idDocumento) {
					documento = doc;
					break;
				}
				indice++;
			}
			if(documento != null) {
				documento.setEstado(eEstado.Retirado); // al elemento encontrado se le cambia el estado.
				documentos.set(indice, documento); // en la lista se remplaza el elemento ya actualizado.
				String nuevoContenido =""; // variable para nuevo contenio que se escribira en el archivo.
				for(Documento docum:documentos) { // se recorre cada documento para rellenar contenido.
					String documentoStr = String.format("%d;%s;%d;%s;%d\n",
							docum.getId(),
							docum.getDescripcion(),
							docum.getTipo().ordinal(),
							docum.getResponsable().getCorreo(),
							docum.getEstado().ordinal()
							); // formatear valores de usuario
					nuevoContenido = String.format("%s%s\n", nuevoContenido,documentoStr);
				}
				FileManager fmDocumento = new FileManager();
				fmDocumento.createAndOverride(this.nombreArchivo,nuevoContenido); // escribir contenido.
				respuesta.setExito(true);
			}
			
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Respuesta limpiar() {
		Respuesta respuesta = new Respuesta(false, "");
		try {
			FileManager fmDocumento = new FileManager();
			fmDocumento.createAndOverride(this.nombreArchivo,""); // escribir contenido.
			respuesta.setExito(true);
			crearArchivoLimpiesa();
		} catch (Exception e) {
			respuesta.setExito(false);
		}
		
		return respuesta; 
	}
	
	public Documento getDocumento(int id) {
		Documento documento = null;
		try {
			List<Documento> documentos = getDocumentos(eTipoDocumento.Todos);
			for( Documento doc : documentos ) {
				if(doc.getId() == id) {
					documento = doc;
					break;
				}
			}
		} catch (Exception e) {
			
		}
		
		return documento; 
	}
	
	public Documento getDocumento(int id,List<Documento> documentos) {
		Documento documento = null;
		try {
			for( Documento doc : documentos ) {
				if(doc.getId() == id) {
					documento = doc;
					break;
				}
			}
		} catch (Exception e) {
			
		}
		
		return documento; 
	}
	
	public int cantidadDocumentosPorTipo(eTipoDocumento tipo, List<Documento> todosDocumentos) {
		int conteo =0;
		
		try {
			for(Documento documento:todosDocumentos) {
				if(tipo == documento.getTipo()) {
					conteo++;
				}
			}
		} catch (Exception e) {
			
		}
		
		return conteo;
	}
	
	public List<Documento>  getDocumentosPorTipo(eTipoDocumento tipo, List<Documento> todosDocumentos) {
		List<Documento> documentos = new ArrayList<>();
		
		try {
			for(Documento documento:todosDocumentos) {
				if(tipo == eTipoDocumento.Todos) {
					documentos.add(documento);
				}
				else {
					if(tipo == documento.getTipo()) {
						documentos.add(documento);
					}
				}				
			}
		} catch (Exception e) {
			
		}
		
		return documentos;
	}
	
	public List<Documento>  getDocumentosPorEstado(eEstado estado, List<Documento> todosDocumentos) {
		List<Documento> documentos = new ArrayList<>();
		
		try {
			for(Documento documento:todosDocumentos) {
				if(documento.getEstado() == estado) {
					documentos.add(documento);
				}
			}
		} catch (Exception e) {
			
		}
		
		return documentos;
	}
	
	public int getCantidadDocumentosPorEstado(eEstado estado,List<Documento> todosDocumentos) {
		int conteo =0;		
		try {
			for(Documento documento:todosDocumentos) {
				if(documento.getEstado() == estado) {
					conteo++;
				}
			}
		} catch (Exception e) {
			
		}		
		return conteo;
	}
	
	public void crearArchivoLimpiesa() {
		try {
			LocalDateTime fecha = LocalDateTime.now();
			DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
			String fechaStr = String.format("", fecha.getYear());
			String nombreArchivoLog ="eclipse/Folder/RemovidoEl_"+fecha.format(formatter) + ".log";
			
			FileManager fmArchivoLog = new FileManager();
			fmArchivoLog.createAndOverride(nombreArchivoLog, "");
		} catch (Exception e) {
			String prueba = e.getMessage();
			String prueba2 = prueba;
		}
		
	}
	
	public List<Documento> getDocumentos(eTipoDocumento tipo) {
		List<Documento> documentos = new ArrayList<>();
		try {
			FileManager fmDocumento = new FileManager();
			String contenido = fmDocumento.read(this.nombreArchivo).getContent();
			String[] valores = contenido.split("\n");
			for (String linea : valores) {
				if(!linea.equals("")) {
					Documento documento = new Documento();
					
					String[] vlsDocumento = linea.split(";");
					int contador = 0;
					for (String valor : vlsDocumento) {
						
						if(contador == 1) {
							documento.setDescripcion(valor);
						}
						else {
							if(contador == 0) {
								documento.setId(Integer.parseInt(valor));
							}
							else {
								if(contador == 2) {
									int tipoDocInt = Integer.parseInt(valor); // se obtiene el entero ordinal del enumerador
									eTipoDocumento tipoDoc = eTipoDocumento.values()[tipoDocInt];
									documento.setTipo(tipoDoc);
								}
								else {
									if(contador == 3) {
										documento.setResponsable(new Usuario(0, "", valor));
									}
									else {
										if(contador == 4) {
											int estadoInt = Integer.parseInt(valor); // se obtiene el entero ordinal del enumerador
											eEstado estado = eEstado.values()[estadoInt];
											documento.setEstado(estado);
										}
									}
								}
							}
						}	
						contador++;
					}
					// se obtienen documentos solo del tipo enviado de parametro
					if(tipo == eTipoDocumento.Todos) {
						documentos.add(documento);
					}
					else {
						if(tipo == documento.getTipo()) {
							documentos.add(documento);
						}
					}
					//**********************************************************
				}
					
			}
			// establecer la lista de acuerdo al contenido...
		} catch (Exception e) {
			String error = e.getMessage();
		}
		
		return documentos; 
	}

	@Override
	public Respuesta validaVacios(Object valor) {
		Respuesta respuesta = new Respuesta(true, "");
		try {
			Documento documento =(Documento) valor;
			// Valida requeridos.
			if(documento.getDescripcion().trim().equals("")) {
				respuesta.setMensaje("El campo informacion de registro es requerido");
				respuesta.setExito(false);
			}
			if(documento.getTipo()==null) {
				respuesta.setMensaje("El campo tipo es requerido");
				respuesta.setExito(false);
			}
			if(documento.getResponsable().getCorreo().trim().equals("")) {
				respuesta.setMensaje("El campo correo es requerido");
				respuesta.setExito(false);
			}
			//*****************
			
		} catch (Exception e) {
			respuesta.setExito(false);
			respuesta.setMensaje("Error con los datos de documentos");
		}
		
		return respuesta; 
	}
}
