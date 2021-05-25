package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemEmployee;


public interface SystemEmployeeService {
	DataResult<List<SystemEmployee>> getAll();
	DataResult <Optional<SystemEmployee>> getByUserId(int userId);
	Result add(SystemEmployee systemEmployee);
	Result update(SystemEmployee systemEmployee);
	Result delete(SystemEmployee systemEmployee);

}
