package programLibraries;

/**
 * Codigo tomado de la clase
 * @author jose.inestroza@unah.edu.hn
 * */

public abstract class Validator {

    public boolean isEmail(String text) {
        if (text.trim().matches("[\\w.]+@[\\w]+(\\.[\\w]{2,})+")) {
            return true;
        }
        return false;
    }

    public String removeHTML(String text) {
        return text.trim().replaceAll("<[^>]+>", "");
    }

    public String removeSpecialCharacters(String text) {
        return text.trim().replaceAll("[^\\w \\t.,-:áéíóúÁÉÍÓÚñÑ]+","");
    }
    
    public abstract Respuesta validaVacios(Object valor);
}
