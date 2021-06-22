package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

public interface JobAdvertService {
	
	DataResult<List<JobAdvert>> getAll();
	DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateDesc();
	DataResult<JobAdvert> getByJobAdvertId(int jobAdvertId);
	
	DataResult<List<JobAdvert>> getByUserIdAndSortByAdvertDateDesc(int userId);
	DataResult<List<JobAdvert>> getByCompanyNameAndSortByAdvertDateDesc(String companyName);
	
	DataResult<List<JobAdvert>> getAllByCity_CityName(String cityName);
	DataResult<List<JobAdvert>> getAllByCity_CityId(int cityId);
 	
	Result add(JobAdvert jobAdvert);
	Result closeAdvert(int jobAdvertId);
	Result closeAdvertAdmin(int jobAdvertId);
	Result confirmAdvert(int jobAdvertId);

}
