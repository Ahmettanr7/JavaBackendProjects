package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.EmployerUpdate;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer>{
	
	List<EmployerUpdate> getByUserIdOrderByUpdateDateDesc(int userId);
	
	EmployerUpdate getByUpdateId(int updateId);
	
	List<EmployerUpdate> getAllByApprovalStatusIsFalseOrderByUpdateDate();
	
	

}
