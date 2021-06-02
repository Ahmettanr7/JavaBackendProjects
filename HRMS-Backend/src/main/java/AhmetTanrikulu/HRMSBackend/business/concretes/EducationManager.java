package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EducationService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EducationDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{
	
	private EducationDao educationDao;

	@Autowired
	public EducationManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}

	@Override
	public Result add(Education education) {
		if(education.isGraduationStatus()) {
			education.setEducationStatus("Mezun");
			this.educationDao.save(education);
		}else {
		education.setEducationStatus("Devam ediyor");
		 this.educationDao.save(education); }
		 return new SuccessResult("Eğitim bilgisi eklendi");
	}

	@Override
	public DataResult<List<Education>> getAll() {
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll(),"Eğitimler getirlidi");
	}

	@Override
	public DataResult<List<Education>> getAllByUserIdOrderByGraduationDateDesc(int userId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getAllByUserIdOrderByGraduationDateDesc(userId));
	}

}
