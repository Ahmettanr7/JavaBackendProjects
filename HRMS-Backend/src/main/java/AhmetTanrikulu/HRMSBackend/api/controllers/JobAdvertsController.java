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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.JobAdvertService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobadverts/")
@CrossOrigin
public class JobAdvertsController {
	
	private JobAdvertService jobAdvertService;

	@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService) {
		super();
		this.jobAdvertService = jobAdvertService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvert>> getAll(){
		return this.jobAdvertService.getAll();
	}
	
	@GetMapping("getActiviteAdvertsByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateDesc(){
		return this.jobAdvertService.getActiviteAdvertsByAdvertDateDesc();
	}
	
	@GetMapping("getByUserIdAndSortByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getByUserIdAndSortByAdvertDateDesc(int userId){
		return this.jobAdvertService.getByUserIdAndSortByAdvertDateDesc(userId);
	}
	
	@GetMapping("getByCompanyNameAndSortByAdvertDateDesc")
	public DataResult<List<JobAdvert>> getByCompanyNameAndSortByAdvertDateDesc(String companyName){
		return this.jobAdvertService.getByCompanyNameAndSortByAdvertDateDesc(companyName);
	}
	
	@GetMapping("getallbycityname")
	public DataResult<List<JobAdvert>> getAllByCityName(String cityName){
		return this.jobAdvertService.getAllByCity_CityName(cityName);
	}
	
	@GetMapping("getallbycityid")
	public DataResult<List<JobAdvert>> getAllByCityId(@RequestParam int cityId){
		return this.jobAdvertService.getAllByCity_CityId(cityId);
	}
	
	@GetMapping("getbyjobadvertid")
	public DataResult<JobAdvert> getByJobAdvertId(int jobAdvertId){
		return this.jobAdvertService.getByJobAdvertId(jobAdvertId);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvert jobAdvert) {
		return ResponseEntity.ok(this.jobAdvertService.add(jobAdvert));
	}
	
	@PostMapping("confirmAdvert")
	public Result confirmAdvert(@RequestParam int jobAdvertId) {
		return this.jobAdvertService.confirmAdvert(jobAdvertId);
	}
	
	@PostMapping("closeAdvertAdmin")
	public Result closeAdvertAdmin( @RequestParam int jobAdvertId) {
		return this.jobAdvertService.closeAdvertAdmin(jobAdvertId);
	}
	
	@PostMapping("closeAdvert")
	public Result closeAdvert( @RequestParam int jobAdvertId) {
		return this.jobAdvertService.closeAdvert(jobAdvertId);
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
