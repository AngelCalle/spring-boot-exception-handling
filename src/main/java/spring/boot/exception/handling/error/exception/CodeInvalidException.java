package spring.boot.exception.handling.error.exception;

public class CodeInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION = "Code Invalid Exception";

    public CodeInvalidException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}