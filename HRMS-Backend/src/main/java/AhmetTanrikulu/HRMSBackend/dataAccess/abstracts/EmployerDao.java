package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Employer;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	List<Employer> findAllByTaxNumber(String taxNumber);
	List<User> findAllByEmail(String email);

}
