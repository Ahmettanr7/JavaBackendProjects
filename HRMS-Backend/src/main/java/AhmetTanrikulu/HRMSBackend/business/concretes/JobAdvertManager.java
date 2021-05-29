package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.JobAdvertService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.JobAdvertDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService{
	
	private JobAdvertDao jobAdvertDao;

	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
		super();
		this.jobAdvertDao = jobAdvertDao;
	}

	@Override
	public Result add(JobAdvert jobAdvert) {
		var result = BusinessRules.run(
				checkIfInfoIsNull(jobAdvert)
				);
		if (result != null) {
			return result;
		}
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan yayınlandı");
		
	}
	
	@Override
	public Result closeAdvert(JobAdvert jobAdvert) {
		jobAdvert.setActivityStatus(false);
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan yayından kaldırıldı");
		
	}
	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(),"İlanlar tablosunun bütün verileri listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByActivityStatusIsTrue() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrue(),"İlanlar tablosunun aktif bütün ilanları listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByActivityStatusIsTrueOrderByAdvertDateAsc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrueOrderByAdvertDateAsc(),"İlanlar tablosunun aktif bütün ilanları tarihi artan sırayla listelendi");
	}

	@Override
	public DataResult<List<JobAdvert>> getByActivityStatusIsTrueOrderByAdvertDateDesc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrueOrderByAdvertDateDesc(),"İlanlar tablosunun aktif bütün ilanları tarihi azalan sırayla listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByEmployer_UserId(int userId) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByEmployer_UserId(userId),"Seçtiğiniz firmaya ait ilanlar listelendi");
	}
	
	private Result checkIfInfoIsNull(JobAdvert jobAdvert) {
		if (jobAdvert.getDescription().isBlank()) {
			return new ErrorResult("Lütfen tüm alanları doldurun");
		} else {
			return new SuccessResult();
		}
	}

	

	

	
	
}

