package AhmetTanrikulu.HRMSBackend.business.abstracts;


import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;

public interface EmployeeService {
	List<Employee> getAll();
	Employee getById(int id);
	void add(Employee employee);
	void update(Employee employee);
	void delete(Employee employee);

}
