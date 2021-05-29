package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{
	
	List<JobAdvert> getByActivityStatusIsTrue();
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateAsc();
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateDesc();
	List<JobAdvert> getByEmployer_UserId(int userId);

}
