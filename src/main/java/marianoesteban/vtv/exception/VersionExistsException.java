package marianoesteban.vtv.exception;

public class VersionExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VersionExistsException(String errorMessage) {
		super(errorMessage);
	}
}
