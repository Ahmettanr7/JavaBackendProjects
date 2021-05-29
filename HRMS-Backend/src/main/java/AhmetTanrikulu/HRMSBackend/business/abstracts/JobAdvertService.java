package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

public interface JobAdvertService {
	
	DataResult<List<JobAdvert>> getAll();
	
	DataResult<List<JobAdvert>> getByActivityStatusIsTrue();
	DataResult<List<JobAdvert>> getByActivityStatusIsTrueOrderByAdvertDateAsc();
	DataResult<List<JobAdvert>> getByActivityStatusIsTrueOrderByAdvertDateDesc();
	
	DataResult<List<JobAdvert>> getByEmployer_UserId(int userId);
 	
	Result add(JobAdvert jobAdvert);
	Result closeAdvert(JobAdvert jobAdvert);

}
