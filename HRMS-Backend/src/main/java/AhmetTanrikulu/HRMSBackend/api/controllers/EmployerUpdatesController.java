package AhmetTanrikulu.HRMSBackend.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployerUpdateService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmployerUpdate;

@RestController
@RequestMapping("/api/employerupdates/")
@CrossOrigin
public class EmployerUpdatesController {
	
	private EmployerUpdateService employerUpdateService;
	
	public EmployerUpdatesController(EmployerUpdateService employerUpdateService) {
		super();
		this.employerUpdateService = employerUpdateService;
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody EmployerUpdate employerUpdate) {
		return ResponseEntity.ok(this.employerUpdateService.add(employerUpdate));
	}
	
	@PostMapping("confirm")
	public Result confirm( @RequestParam int id) {
		return this.employerUpdateService.confirm(id);
	}
	
	@GetMapping("getbyuserid")
	public Result getByUserId(@RequestParam int userId){
		return this.employerUpdateService.getByUserId(userId);
	}
	
	@GetMapping("getallpendingupdates")
	public Result getAllPendingUpdates(){
		return this.employerUpdateService.getAllByApprovalStatusIsFalseOrderByUpdateDate();
	}

}
