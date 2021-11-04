package spring.boot.exception.handling.error.exception;

public class FieldInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    private static final String DESCRIPTION = "excepción de campo no válido";

    public FieldInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}