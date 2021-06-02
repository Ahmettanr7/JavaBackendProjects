package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Experience;

public interface ExperienceService {
	Result add(Experience experience);
	
	
	DataResult<List<Experience>> getAll();
	
	DataResult<List<Experience>> getAllByUserIdOrderByQuitDate(int userId);
	

}
