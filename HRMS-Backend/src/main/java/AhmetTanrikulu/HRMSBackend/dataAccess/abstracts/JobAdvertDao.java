package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{
	
	JobAdvert getByJobAdvertId(int jobAdvertId);
	
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateDesc();
	
	@Query("From JobAdvert Where jobAdvertId = :jobAdvertId and activityStatus = 1")
	JobAdvert getByJobAdvertIdAndActive(int jobAdvertId);
	
	//forEmployer
	List<JobAdvert> getByEmployer_UserIdOrderByAdvertDateDesc(int userId);
	List<JobAdvert> getByEmployer_CompanyNameOrderByAdvertDateDesc(String companyName);
	
	//forCity
	List<JobAdvert> getAllByCity_CityName(String cityName);
	List<JobAdvert> getAllByCity_CityId(int cityId);
	
	//forPosition
	@Query("From JobAdvert Where position.positionId = :positionId and activityStatus = 1")
	List<JobAdvert> getAllByActivityStatusIsTrueAndPosition_PositionId(int positionId);
	
}
