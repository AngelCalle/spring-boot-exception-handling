package spring.boot.exception.handling.error.exception;

public class InvalidFieldException extends BadRequestException {

	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_TYPE = "Invalid Field Exception.";
	
	public InvalidFieldException(String errorDetails) {
		super(ERROR_TYPE + " " + errorDetails);
	}

}