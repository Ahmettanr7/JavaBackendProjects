package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import AhmetTanrikulu.HRMSBackend.business.abstracts.JobAdvertService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;
import AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto;

@RestController
@RequestMapping("/api/jobadverts/")
public class JobAdvertsController {
	
	private JobAdvertService jobAdvertService;

	public JobAdvertsController(JobAdvertService jobAdvertService) {
		super();
		this.jobAdvertService = jobAdvertService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvert>> getAll(){
		return this.jobAdvertService.getAll();
	}
	
	@GetMapping("getJobAdvertDto")
	public DataResult<List<JobAdvertDto>> getJobAdvertDto(){
		return this.jobAdvertService.getJobAdvertDto();
	}
	
	@GetMapping("getJobAdvertDtoActiveAdvertsByDate")
	public DataResult<List<JobAdvertDto>> getJobAdvertDtoActiveAdvertsByDate(){
		return this.jobAdvertService.getJobAdvertDtoActiveAdvertsByDate();
	}
	
	@GetMapping("getByActiviteAdverts")
	public DataResult<List<JobAdvert>> getByActiviteAdverts(){
		return this.jobAdvertService.getByActiviteAdverts();
	}
	
	@GetMapping("getActiviteAdvertsByAdvertDateAsc")
	public DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateAsc(){
		return this.jobAdvertService.getActiviteAdvertsByAdvertDateAsc();
	}
	
	@GetMapping("getActiviteAdvertsByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateDesc(){
		return this.jobAdvertService.getActiviteAdvertsByAdvertDateDesc();
	}
	
	@GetMapping("getByUserIdAndSortByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getByEmployerIdOrderByAdvertDateDesc(int userId){
		return this.jobAdvertService.getByUserIdAndSortByAdvertDateDesc(userId);
	}
	
	@GetMapping("getByCompanyNameAndSortByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getByCompanyNameAndSortByAdvertDateDesc(String companyName){
		return this.jobAdvertService.getByCompanyNameAndSortByAdvertDateDesc(companyName);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvert jobAdvert) {
		return ResponseEntity.ok(this.jobAdvertService.add(jobAdvert));
	}
	
	@PostMapping("closeAdvert")
	public Result closeAdvert(@RequestBody JobAdvert jobAdvert) {
		return this.jobAdvertService.closeAdvert(jobAdvert);
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
