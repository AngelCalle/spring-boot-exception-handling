package spring.boot.exception.handling.error.exception;

public class FieldalreadyExistException extends RuntimeException {
    private static final String DESCRIPTION = "el campo ya existe excepción (409)";

    public FieldalreadyExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}