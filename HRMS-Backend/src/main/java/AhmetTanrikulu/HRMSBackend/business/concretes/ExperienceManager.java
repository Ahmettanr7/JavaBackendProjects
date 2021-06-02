package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.ExperienceService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.ExperienceDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Experience;

@Service
public class ExperienceManager implements ExperienceService{
	

	private ExperienceDao experienceDao;
	
	@Autowired
	public ExperienceManager(ExperienceDao experienceDao) {
		super();
		this.experienceDao = experienceDao;
	}

	
	@Override
	public Result add(Experience experience) {
		if(experience.isWorkingStatus_b()) {
			experience.setWorkingStatus("Devam ediyor");
			this.experienceDao.save(experience);
		}else{
		experience.setWorkingStatus("Devam etmiyor");
		this.experienceDao.save(experience); }
		return new SuccessResult("Tecrübeniz eklendi");
	}

	@Override
	public DataResult<List<Experience>> getAll() {
		return new SuccessDataResult<List<Experience>>(this.experienceDao.findAll(),"Tecrübeler getirildi");
	}


	@Override
	public DataResult<List<Experience>> getAllByUserIdOrderByQuitDate(int userId) {
		return new SuccessDataResult<List<Experience>>(this.experienceDao.getAllByUserIdOrderByQuitDate(userId));
	}




	

}
