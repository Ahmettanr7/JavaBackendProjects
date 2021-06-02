package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SingleInformationService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorDataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;

@RestController
@RequestMapping("/api/cv/")
public class SingleInformationsController {
	
	private SingleInformationService singleInformationService;

	@Autowired
	public SingleInformationsController(SingleInformationService singleInformationService) {
		super();
		this.singleInformationService = singleInformationService;
	}
	
	@GetMapping("getall")
	public DataResult<List<SingleInformation>> getAll(){
		return this.singleInformationService.getAll();
	}
	
	@GetMapping("getbyuserid")
	public DataResult<SingleInformation> getByUserId(int userId){
		return this.singleInformationService.getByUserId(userId);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody SingleInformation singleInformation) {
		return ResponseEntity.ok(this.singleInformationService.add(singleInformation));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors = new HashMap<String, String>();
		 for(FieldError fieldError :  exceptions.getBindingResult().getFieldErrors() ) {
			 validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		 }
		 
		 ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		 return errors;
	}

}
