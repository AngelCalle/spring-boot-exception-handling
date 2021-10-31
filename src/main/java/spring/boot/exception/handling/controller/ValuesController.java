package spring.boot.exception.handling.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
public class ValuesController {

	@Autowired
	private HttpServletResponse httpServletResponse;

//	Documentacion
//	https://howtodoinjava.com/spring-boot2/spring-rest-request-validation/
//	https://howtodoinjava.com/spring-boot2/spring-rest-request-validation/
//	https://reflectoring.io/bean-validation-with-spring-boot/

	// http://localhost:8080/validatePathVariable/2 false
	// http://localhost:8080/validatePathVariable/5 true
	@GetMapping("/validatePathVariable/{id}")
	ResponseEntity<String> validatePathVariable(@PathVariable("id") @Min(5) int id) {
		return ResponseEntity.ok("valid");
	}

	// http://localhost:8080/validateRequestParameter false
	// http://localhost:8080/validateRequestParameter?param=asdf false
	// http://localhost:8080/validateRequestParameter?param=1
	// http://localhost:8080/validateRequestParameter?param=5 true
	@GetMapping("/validateRequestParameter")
	ResponseEntity<String> validateRequestParameter(@RequestParam("param") @Min(5) int param) {
		return ResponseEntity.ok("valid");
	}

	// http://localhost:8080/employees false
	// http://localhost:8080/employees?param false
	// http://localhost:8080/employees?param=5 false
	// http://localhost:8080/employees?id=5 true
	@GetMapping(value = "/employees")
	public ResponseEntity<String> addEmployee(@Valid @RequestParam(name = "id") String fooId) {
		return ResponseEntity.ok("valid");
	}

	// http://localhost:8080/caramelo?id=5 false
	// http://localhost:8080/caramelo?id=ddd false
	// http://localhost:8080/caramelo?id=DD false
	// http://localhost:8080/caramelo?id=D true
	@GetMapping("/caramelo")
	public ResponseEntity<String> getFoos(@RequestParam(required = true) @Pattern(regexp = "[A-Z]") String id) {
		return ResponseEntity.ok("valid");
	}

	// http://localhost:8080/coco?id=D false
	// http://localhost:8080/coco?id=DGDF true
	@GetMapping("/coco")
	public ResponseEntity<String> coco(@RequestParam(required = true) @Pattern(regexp = "[A-Z]{3,50}") String id) {
		return ResponseEntity.ok("valid");
	}

	// http://localhost:8080/caca?id=AA4 false
	// http://localhost:8080/caca?id=AAS true
	@GetMapping("/caca")
	public ResponseEntity<String> caca(
			@RequestParam(required = true) @Pattern(regexp = "[A-Z]{3,50}") @Size(min = 3, max = 50) String id) {
		return ResponseEntity.ok("valid");
	}
	
	
//	Error handling with ResponseStatusException, is the newest, since it has been introduced in Spring 5.
// 	@ControllerAdvice es mas limpio. Aunque podemos combinar ambas.
//	@GetMapping(value = "/{id}")
//	public Car findById(@PathVariable("id") Long id) {
//		try {
//			Car carById = RestPreconditions.checkFound(carRepository.findOne(id));
//			eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, httpServletResponse));
//			return carById;
//		} catch (CarNotFoundException ex) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found", ex);
//		}
//	}

}
