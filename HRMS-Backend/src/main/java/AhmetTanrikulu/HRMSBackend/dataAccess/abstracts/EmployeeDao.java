package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

}
