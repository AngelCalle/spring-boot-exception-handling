package spring.boot.exception.handling.error;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

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

	private String message;

	private List<String> details;

}