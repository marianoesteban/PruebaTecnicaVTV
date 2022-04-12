package marianoesteban.vtv.exception;

public class ModeloExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModeloExistsException(String errorMessage) {
		super(errorMessage);
	}
}
