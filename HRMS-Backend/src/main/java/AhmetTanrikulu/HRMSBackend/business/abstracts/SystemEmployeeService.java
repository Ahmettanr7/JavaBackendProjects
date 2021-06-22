package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemEmployee;


public interface SystemEmployeeService {
	
	DataResult<List<SystemEmployee>> getAll();
	
	DataResult<SystemEmployee> getByUserId(int userId);
	
	DataResult<SystemEmployee> getByEmail(String email);
	
	Result add(SystemEmployee systemEmployee);
	Result delete(SystemEmployee systemEmployee);

}
