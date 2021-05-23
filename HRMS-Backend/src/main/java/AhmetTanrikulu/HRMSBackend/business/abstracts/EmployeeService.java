package AhmetTanrikulu.HRMSBackend.business.abstracts;


import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;

public interface EmployeeService {
	DataResult<List<Employee>> getAll();
	DataResult<Optional<Employee>> getByUserId(int id);
	Result add(Employee employee);
	Result update(Employee employee);
	Result delete(Employee employee);

}
