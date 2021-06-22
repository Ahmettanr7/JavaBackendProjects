package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.Date;
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
		Date now=java.util.Calendar.getInstance().getTime();
		curriculumVitae.setCreatedDate(now);
		this.singleInformationDao.save(curriculumVitae);
		return new SuccessResult("Cv oluşturuldu");
	}
	
	@Override
	public Result delete(int id) {
		this.singleInformationDao.deleteById(id);
		return new SuccessResult("Github Linkedin hesaplarınız ve Önyazınız silindi");
	}

	@Override
	public DataResult<SingleInformation> getByUserId(int userId) {
		return new SuccessDataResult<SingleInformation>(this.singleInformationDao.getByUserId(userId));
	}

}
