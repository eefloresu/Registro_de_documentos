package programLibraries;

import java.io.File;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Codigo tomado de la clase
 * @author jose.inestroza@unah.edu.hn
 * */

public class FileManager {
    /**
     * @return La ruta del sistema donde se estan guardando, por defecto, los archivos.
     */
    public String path(){
        File file = new File(".");
        return file.getAbsolutePath();
    }

    public FileManagerResponse read(String filename) {
        try {
            FileManagerResponse response = new FileManagerResponse(filename); 
            response.setPath(String.format("%s/%s", this.path(), filename));
            response.setContent(Files.readString(Paths.get(response.getPath()))); 
            response.setStatus(true);

            return response;
        } catch (IOException e) {
            return new FileManagerResponse(e.toString());
        }
    }

    public FileManagerResponse create(String filename, String content) {
        try {
            FileManagerResponse response = new FileManagerResponse(content);  

            response.setPath(
                Files.writeString(
                    Files.createFile(
                        Paths.get(
                            String.format("%s/%s", this.path(), filename)
                        )
                    ),
                    content
                ).toString()
            );
            response.setStatus(true);

            return response;
        } catch (FileAlreadyExistsException e) {
            
            //return new FileManagerResponse("El arcrivo que usted esta creando, ya existe.");
            return this.createAndOverride(filename, content);
        }catch (IOException e) {
            return new FileManagerResponse(e.toString());
        }
    }

    public FileManagerResponse createAndOverride(String filename, String content) {
        try {
            FileManagerResponse response = new FileManagerResponse(content);

            response.setPath(
                Files.writeString(
                    Paths.get(
                        String.format("%s/%s", this.path(), filename)
                    ),
                    content
                ).toString()
            );
            response.setStatus(true);

            return response;
        } catch (IOException e) {

            return new FileManagerResponse(e.toString());
        }
    }
}