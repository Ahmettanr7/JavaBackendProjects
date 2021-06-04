package AhmetTanrikulu.HRMSBackend.business.abstracts;


import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;
import AhmetTanrikulu.HRMSBackend.entities.dtos.CurriculumVitaeDto;

public interface EmployeeService {
	DataResult<List<Employee>> getAll();
	Result add(Employee employee);
	Result update(Employee employee);
	Result delete(Employee employee);
	
	DataResult<Employee> getByUserId(int userId);
	
	DataResult<CurriculumVitaeDto> getCurriculumVitaeByUserId(int userId);

}
