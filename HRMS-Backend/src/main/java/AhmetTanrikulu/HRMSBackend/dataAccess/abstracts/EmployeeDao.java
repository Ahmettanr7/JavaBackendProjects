package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	List<User> findAllByEmail(String email);
	
	List<Employee> findAllByNationalityId(String nationalityId);
	
	Employee getByUserId(int userId);
	
}
