package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;

public interface SingleInformationService {
	
	Result add(SingleInformation singleInformation);
	
	Result delete(int id);
	
	DataResult<List<SingleInformation>> getAll();
	
	DataResult<SingleInformation> getByUserId(int userId);
}
