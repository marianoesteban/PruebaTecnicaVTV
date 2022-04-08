package marianoesteban.vtv.exception;

public class DniExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DniExistsException(String errorMessage) {
		super(errorMessage);
	}

}
