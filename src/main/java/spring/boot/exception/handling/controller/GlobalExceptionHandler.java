package spring.boot.exception.handling.controller;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import spring.boot.exception.handling.error.ErrorResponse;
import spring.boot.exception.handling.error.exception.NoSuchElementFoundException;
import spring.boot.exception.handling.error.exception.RecordNotFoundException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Example to have a method to generate all equal bodys
	// https://www.it-swarm-es.com/es/java/como-detectar-todas-las-excepciones-no-controladas-es-decir-sin-exceptionhandler-existente-en-spring-mvc/837828213/

	// https://www.toptal.com/java/spring-boot-rest-api-error-handling
	
	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest requestw) {
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest request,
			WebRequest requestw) {
		System.out.println("\n\n handleAllExceptions");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Server Error handleAllExceptions");
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request) {
		System.out.println("\n\n handleConstraintViolationException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Server Error handleConstraintViolationException");
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) {
		System.out.println("\n\n handleIllegalArgumentException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Server Error handleIllegalArgumentException");
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
		System.out.println("\n\n handleIllegalArgumentException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Server Error handleIllegalArgumentException");
		error.setDetails(details);
		error.setRequestedURI(request.getRequestURI());
		return new ResponseEntity(error, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("\n\n handleMethodArgumentNotValid");
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Validation Failed");
		error.setDetails(details);
		error.setRequestedURI(ex.getLocalizedMessage());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("\n\n handleMissingServletRequestParameter");
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Required parameter");
		error.setDetails(details);
		error.setRequestedURI(ex.getLocalizedMessage());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
		System.out.println("\n\n handleUserNotFoundException");
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse();
		LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		error.setDate(dateTime);
		error.setMessage("Record Not Found");
		error.setDetails(details);
		error.setRequestedURI(ex.getLocalizedMessage());
		return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementFoundException.class)
	public ResponseEntity<String> handleNoSuchElementFoundException2(
			NoSuchElementFoundException exception,
			HttpServletRequest httpServletRequest,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}


//	@RestControllerAdvice
//	public class ExceptionTranslator {
//
//	    @ExceptionHandler(RuntimeException.class)
//	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	    public ErrorDTO processRuntimeException(RuntimeException e) {
//	        return createErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred.", e);
//	    }
//
//	    private ErrorDTO createErrorDTO(HttpStatus status, String message, Exception e) {
//	        (...)
//	    }
//	}
}