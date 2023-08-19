package programLibraries;

/**
 * Codigo tomado de la clase
 * @author jose.inestroza@unah.edu.hn
 * */

public class FileManagerResponse {
    private String path;
    private String content;
    private boolean status;
    private String error;

    public FileManagerResponse() {}

    public FileManagerResponse(String error) {
        this.error = error;
        this.status = false;
    }

	public final String getPath() {
		return path;
	}

	public final void setPath(String path) {
		this.path = path;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final boolean isStatus() {
		return status;
	}

	public final void setStatus(boolean status) {
		this.status = status;
	}

	public final String getError() {
		return error;
	}

	public final void setError(String error) {
		this.error = error;
	}  
}
