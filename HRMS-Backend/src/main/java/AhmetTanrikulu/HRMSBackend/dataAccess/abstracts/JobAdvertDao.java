package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;
import AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{
	
	//getActive
	List<JobAdvert> getByActivityStatusIsTrue();
	
	//getActiveSortDesc
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateAsc();
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateDesc();
	
	//getByCompany
	List<JobAdvert> getByEmployer_UserIdOrderByAdvertDateDesc(int userId);
	List<JobAdvert> getByEmployer_CompanyNameOrderByAdvertDateDesc(String companyName);
	
	
	JobAdvert getByJobAdvertId(int jobAdvertId);
	
	//getById&Acitive
	@Query("From JobAdvert Where jobAdvertId = :jobAdvertId and activityStatus = 1")
	JobAdvert getByJobAdvertIdAndActive(int jobAdvertId);
	
	//PandingApprovalSortDesc
	List<JobAdvert> getByActivityStatusIsFalseOrderByAdvertDateDesc();
	
	//getByCity
	List<JobAdvert> getAllByCity_CityName(String cityName);
	List<JobAdvert> getAllByCity_CityId(int cityId);
	
	//getByPosition&Active
	@Query("From JobAdvert Where position.positionId = :positionId and activityStatus = 1")
	List<JobAdvert> getAllByActivityStatusIsTrueAndPosition_PositionId(int positionId);

	//getAllDto
	@Query("Select new AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto"
			+ "(jobAdvertId,employer.companyName,position.positionName,quantity,advertDate,dueDate,activityStatus,city.cityName,"
			+ "description,minSalary,maxSalary) "
			+ "From JobAdvert")
	List<JobAdvertDto> getJobAdvertDto();
	
	
	//getActiveDto
	@Query("Select new AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto"
			+ "(jobAdvertId,employer.companyName,position.positionName,quantity,advertDate,dueDate,activityStatus,city.cityName,"
			+ "description,minSalary,maxSalary) "
			+ "From JobAdvert "
			+ "Where activityStatus = 1 Order By advertDate Desc")
	List<JobAdvertDto> getJobAdvertDtoActiveAdvertsByDate();
	
}
