package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Ability;

public interface AbilityService {
	Result add(Ability ability);
	
	Result add(List<Ability> abilities);
	
	DataResult<List<Ability>> getAll();
	
	DataResult<List<Ability>> getAllByUserId (int user_id);

}
