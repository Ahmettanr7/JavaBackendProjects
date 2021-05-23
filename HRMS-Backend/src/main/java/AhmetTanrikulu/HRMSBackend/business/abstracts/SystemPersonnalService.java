package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;


public interface SystemPersonnalService {
	DataResult<List<SystemPersonnal>> getAll();
	DataResult <Optional<SystemPersonnal>> getByUserId(int userId);
	Result add(SystemPersonnal systemPersonnal);
	Result update(SystemPersonnal systemPersonnal);
	Result delete(SystemPersonnal systemPersonnal);

}
