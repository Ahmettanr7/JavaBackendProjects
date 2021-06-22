package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Language;

public interface LanguageService {
	Result add(Language language);
	
	Result delete(int id);
	
	DataResult<List<Language>> getAll();
	
	DataResult<List<Language>> getAllByUserId(int userId);

}
