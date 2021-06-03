package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		if(education.getGraduationDate() == null) {
			education.setGraduationStatus(false);
			education.setEducationStatus("Devam ediyor");
		}else {
			education.setGraduationStatus(true);
			education.setEducationStatus("Mezun");
		}
		 this.educationDao.save(education);
		 return new SuccessResult("Eğitim bilgisi eklendi");
	}

	@Override
	public DataResult<List<Education>> getAll() {
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll(),"Eğitimler getirlidi");
	}

	@Override
	public DataResult<List<Education>> getAllByUserIdOrderByGraduationDateDesc(int userId) {
		Sort sort =Sort.by(Sort.Direction.DESC,"graduationDate");
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll(sort));
	}

}
