
package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SystemEmployeeService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemEmployee;

@RestController
@RequestMapping("/api/systememployees")
public class SystemEmployeesController {
	
	private SystemEmployeeService systemEmployeeService;

	@Autowired
	public SystemEmployeesController(SystemEmployeeService systemEmployeeService) {
		super();
		this.systemEmployeeService = systemEmployeeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<SystemEmployee>> getAll(){
		return this.systemEmployeeService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody SystemEmployee systemPersonnal) {
		return this.systemEmployeeService.add(systemPersonnal);
	}
	

}

