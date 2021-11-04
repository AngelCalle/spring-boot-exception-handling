package spring.boot.exception.handling.controller;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import org.apache.tomcat.websocket.AuthenticationException;
import spring.boot.exception.handling.error.ErrorResponse;
import spring.boot.exception.handling.error.exception.BadGatewayException;
import spring.boot.exception.handling.error.exception.ConflictException;
import spring.boot.exception.handling.error.exception.EntityNotFoundException;
import spring.boot.exception.handling.error.exception.ForbiddenException;
import spring.boot.exception.handling.error.exception.MalformedHeaderException;
import spring.boot.exception.handling.error.exception.NoSuchElementFoundException;
import spring.boot.exception.handling.error.exception.NotFoundUserIdException;
import spring.boot.exception.handling.error.exception.RecordNotFoundException;
import spring.boot.exception.handling.error.exception.UnauthorizedException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		List<String> details = new ArrayList<>();
		details.add("Unsupported content type: " + ex.getContentType());
		details.add("Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes()));

		return buildResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
				new RuntimeException("Error de inicio de sesión, nombre de usuario o contraseña incorrectos"), details,
				ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		// "Malformed JSON request"
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		return buildResponseEntity(HttpStatus.BAD_REQUEST,
				new RuntimeException("Server Error handleConstraintViolationException."), details,
				ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest webRequest) {
		return buildResponseEntity(status, ex, null, ex.getLocalizedMessage());
	}

	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest request,
			WebRequest webRequest) {

		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());

		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
				new RuntimeException("Se presento un problema, reporte e intente luego."), details,
				request.getRequestURI());
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Object> handleThrowable(final Throwable ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.NOT_FOUND,
				new RuntimeException("Server Error handleConstraintViolationException"), details,
				request.getContextPath());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

		for (ConstraintViolation<?> item : violations) {
			details.add(item.getMessage());
		}

		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
				new RuntimeException("Server Error handleConstraintViolationException"), details,
				request.getRequestURI());
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());

		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
				new RuntimeException("Server Error handleConstraintViolationException"), details,
				request.getRequestURI());
	}

	@ExceptionHandler({ RecordNotFoundException.class })
	public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest webRequest) {

		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());

		return buildResponseEntity(HttpStatus.BAD_REQUEST,
				new RuntimeException("Server Error handleConstraintViolationException"), details,
				ex.getLocalizedMessage());
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());

		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,
				new RuntimeException("Server Error handleConstraintViolationException"), details,
				request.getRequestURI());
	}

	@ExceptionHandler({ NoSuchElementFoundException.class })
	public ResponseEntity<Object> handleNoSuchElementFoundException2(NoSuchElementFoundException ex,
			HttpServletRequest httpServletRequest, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(status, new RuntimeException("Server Error handleConstraintViolationException"),
				details, webRequest.getContextPath());
	}

	@ExceptionHandler({ RuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.BAD_REQUEST, new RuntimeException("An internal server error occurred"),
				details, request.getContextPath());
	}

	@ResponseBody
	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex,
			HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.BAD_REQUEST,
				new RuntimeException("Error de inicio de sesión, nombre de usuario o contraseña incorrectos"), details,
				request.getContextPath());
	}

	@ExceptionHandler({ NotFoundUserIdException.class })
	public ResponseEntity<Object> handleNotFoundUserIdException(Exception ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.NOT_FOUND, new RuntimeException("  ERROR: NOT_FOUND, "), details,
				request.getContextPath());
	}

	@ExceptionHandler({ UnauthorizedException.class })
	public ResponseEntity<Object> handleUnauthorizedException(Exception ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.UNAUTHORIZED, new RuntimeException("  ERROR: UNAUTHORIZED, "), details,
				request.getContextPath());
	}

	@ExceptionHandler({ MalformedHeaderException.class })
	public ResponseEntity<Object> handleMalformedHeaderException(Exception ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.BAD_REQUEST, new RuntimeException("  ERROR: BAD_REQUEST, "), details,
				request.getContextPath());
	}

	@ExceptionHandler({ ForbiddenException.class })
	public ResponseEntity<Object> handleForbiddenException(Exception ex, HttpServletRequest request) {

		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.FORBIDDEN, new RuntimeException("  ERROR: FORBIDDEN, "), details,
				request.getContextPath());
	}

	@ExceptionHandler({ ConflictException.class })
	public ResponseEntity<Object> handleConflictException(Exception ex, HttpServletRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.CONFLICT, new RuntimeException("  ERROR: CONFLICT, "), details,
				request.getContextPath());
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler({ BadGatewayException.class })
	public ResponseEntity<Object> handleBadGatewayException(Exception ex, HttpServletRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());

		return buildResponseEntity(HttpStatus.BAD_GATEWAY, new RuntimeException("  ERROR: BAD_GATEWAY, "), details,
				request.getContextPath());
	}
	
	// throw new EntityNotFoundException(Bird.class, "id", birdId.toString());
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
		   // "message": "Bird was not found for parameters {id=2}"
		List<String> details = new ArrayList<>();
		details.add(ex.getMessage());
	    
		return buildResponseEntity(
				HttpStatus.BAD_GATEWAY,
				new RuntimeException("  ERROR: BAD_GATEWAY, "),
				details,
				request.getContextPath());
	}
	
	private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exc, List<String> errors,
			String uri) {

		ErrorResponse error = new ErrorResponse();
		error.setStatus(httpStatus.value());
		error.setCode(123);
		error.setTimestamp(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		error.setMessage("USRMSG-" + exc.getMessage());
		error.setRequestedURI(uri);
		error.setDetails(errors);
		return new ResponseEntity<>(error, httpStatus);
	}

}