
package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SystemPersonnalService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;

@RestController
@RequestMapping("/api/systempersonnals")
public class SystemPersonnalsController {
	
	private SystemPersonnalService systemPersonnalService;

	@Autowired
	public SystemPersonnalsController(SystemPersonnalService systemPersonnalService) {
		super();
		this.systemPersonnalService = systemPersonnalService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<SystemPersonnal>> getAll(){
		return this.systemPersonnalService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody SystemPersonnal systemPersonnal) {
		return this.systemPersonnalService.add(systemPersonnal);
	}
	
	@GetMapping("/{id}")
	public Result getByUserId(@PathVariable int id){
		return this.systemPersonnalService.getByUserId(id);
	}
	

}

