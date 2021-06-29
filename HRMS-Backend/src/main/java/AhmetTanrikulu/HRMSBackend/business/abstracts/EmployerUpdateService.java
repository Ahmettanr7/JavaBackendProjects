package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmployerUpdate;

public interface EmployerUpdateService {
	
	DataResult<List<EmployerUpdate>> getByUserId(int userId);//kullanıcı için
	
	DataResult<List<EmployerUpdate>> getAllByApprovalStatusIsFalseOrderByUpdateDate(); //Admin için
	
	Result add(EmployerUpdate employerUpdate);
	
	Result confirm(int id);

}
