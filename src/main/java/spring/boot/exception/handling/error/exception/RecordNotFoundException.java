package spring.boot.exception.handling.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
// Annotate the Exception class with @ResponseStatus indicate in the value property the desired response HTTP status code.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException() {
    }
	
	public RecordNotFoundException(final String exception) {
        super(exception);
    }

	private static final long serialVersionUID = 1L;

}