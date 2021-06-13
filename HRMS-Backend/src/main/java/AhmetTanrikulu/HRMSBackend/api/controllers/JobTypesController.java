package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.JobTypeService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypePlace;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypeTime;

@RestController
@RequestMapping("/api/jobtypes/")
@CrossOrigin
public class JobTypesController {
	
	private JobTypeService jobTypeService;

	public JobTypesController(JobTypeService jobTypeService) {
		super();
		this.jobTypeService = jobTypeService;
	}
	
	@GetMapping("getallplace")
	public DataResult<List<JobTypePlace>> getAllPlace(){
		return this.jobTypeService.getAllPlace();
	}

	@GetMapping("getalltime")
	public DataResult<List<JobTypeTime>> getAllTime(){
		return this.jobTypeService.getAllTime();
	}
}
