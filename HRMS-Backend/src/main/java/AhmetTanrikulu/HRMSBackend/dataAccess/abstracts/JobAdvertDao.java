package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;
import AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{
	
	List<JobAdvert> getByActivityStatusIsTrue();
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateAsc();
	List<JobAdvert> getByActivityStatusIsTrueOrderByAdvertDateDesc();
	List<JobAdvert> getByEmployer_UserIdOrderByAdvertDateDesc(int userId);
	List<JobAdvert> getByEmployer_CompanyNameOrderByAdvertDateDesc(String companyName);
	
	List<JobAdvert> getAllByActivityStatusIsTrueAndPosition_PositionId(int positionId);

	@Query("Select new AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto(j.jobAdvertId,e.companyName,p.positionName,j.quantity,j.advertDate,j.dueDate,j.activityStatus) From Employer e Inner Join e.jobAdverts j, Position p Inner Join p.jobAdverts")
	List<JobAdvertDto> getJobAdvertDto();
	
	@Query("Select new AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto(j.jobAdvertId,e.companyName,p.positionName,j.quantity,j.advertDate,j.dueDate,j.activityStatus) From Employer e Inner Join e.jobAdverts j, Position p Inner Join p.jobAdverts Where j.activityStatus = 1 Order By j.advertDate Desc")
	List<JobAdvertDto> getJobAdvertDtoActiveAdvertsByDate();
}
