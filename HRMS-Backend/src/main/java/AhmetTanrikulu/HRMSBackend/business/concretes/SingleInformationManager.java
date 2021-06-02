package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import AhmetTanrikulu.HRMSBackend.business.abstracts.SingleInformationService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.SingleInformationDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;

@Service
public class SingleInformationManager implements SingleInformationService{
	
	private SingleInformationDao singleInformationDao;

	@Autowired
	public SingleInformationManager(SingleInformationDao singleInformationDao) {
		super();
		this.singleInformationDao = singleInformationDao;
	}

	@Override
	public DataResult<List<SingleInformation>> getAll() {
		return new SuccessDataResult<List<SingleInformation>>(this.singleInformationDao.findAll(),"Cv'ler listelendi");
	}

	@Override
	public Result add(SingleInformation curriculumVitae ) {
		this.singleInformationDao.save(curriculumVitae);
		return new SuccessResult("Cv oluşturuldu");
	}

	@Override
	public DataResult<SingleInformation> getByUserId(int userId) {
		return new SuccessDataResult<SingleInformation>(this.singleInformationDao.getByUserId(userId));
	}

}
