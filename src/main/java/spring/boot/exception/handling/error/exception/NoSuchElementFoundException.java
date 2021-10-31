package spring.boot.exception.handling.error.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// This way of use is useful when we want to manipulate the response headers as well since we can overwrite them in the getRsponseHeaders () method.
public class NoSuchElementFoundException extends ResponseStatusException {

	public NoSuchElementFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}

	@Override
	public HttpHeaders getResponseHeaders() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	private static final long serialVersionUID = 1L;

}