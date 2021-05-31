package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;
import AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto;

public interface JobAdvertService {
	
	DataResult<List<JobAdvert>> getAll();
	
	DataResult<List<JobAdvert>> getByActiviteAdverts();
	DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateAsc();
	DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateDesc();
	
	DataResult<List<JobAdvert>> getByUserIdAndSortByAdvertDateDesc(int userId);
	DataResult<List<JobAdvert>> getByCompanyNameAndSortByAdvertDateDesc(String companyName);
	
	DataResult<List<JobAdvertDto>> getJobAdvertDto();
	DataResult<List<JobAdvertDto>> getJobAdvertDtoActiveAdvertsByDate();
 	
	Result add(JobAdvert jobAdvert);
	Result closeAdvert(JobAdvert jobAdvert);

}
