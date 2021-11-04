package spring.boot.exception.handling.error;

import java.time.LocalDateTime;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "error")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	// El atributo contiene el estado de la llamada de operación.
	private Integer status;
	
	// Codigo propio del api
	private String code;

	// Contiene la fecha y la hora en que ocurrió el error.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	// Mensajes de error fáciles de usar.
	private String message;

	// Url del api donde se proboco el error
	private String requestedURI;

	private List<String> details;
	
}