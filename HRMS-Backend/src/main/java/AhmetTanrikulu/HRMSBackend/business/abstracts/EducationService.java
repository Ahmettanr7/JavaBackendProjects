package AhmetTanrikulu.HRMSBackend.business.abstracts;


import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Education;

public interface EducationService {
	Result add(Education education);
	
	Result deleteByEducationId(int educationId);
	
	DataResult<List<Education>> getAll();
	
	DataResult<List<Education>> getAllByUserIdOrderByGraduationDateAsc(int userId);

}
