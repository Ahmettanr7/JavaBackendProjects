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
import AhmetTanrikulu.HRMSBackend.entities.dtos.JobAdvertDto;

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
				CheckIfRepetition(jobAdvert)
				);
		if (result != null) {
			return result;
		}
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan yayınlandı");
		
	}
	
	@Override
	public Result closeAdvert(JobAdvert jobAdvert) {
		if(jobAdvert.isActivityStatus()==false) {
			return new ErrorResult("İlan zaten yayında değil");
		}
		jobAdvert.setActivityStatus(false);
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan yayından kaldırıldı");
		
	}
	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(),"İlanlar tablosunun bütün verileri listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByActiviteAdverts() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrue(),"İlanlar tablosunun aktif bütün ilanları listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateAsc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrueOrderByAdvertDateAsc(),"İlanlar tablosunun aktif bütün ilanları tarihi artan sırayla listelendi");
	}

	@Override
	public DataResult<List<JobAdvert>> getActiviteAdvertsByAdvertDateDesc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsTrueOrderByAdvertDateDesc(),"İlanlar tablosunun aktif bütün ilanları tarihi azalan sırayla listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByUserIdAndSortByAdvertDateDesc(int userId) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByEmployer_UserIdOrderByAdvertDateDesc(userId),"Seçtiğiniz firmaya ait ilanlar listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByCompanyNameAndSortByAdvertDateDesc(String companyName) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByEmployer_CompanyNameOrderByAdvertDateDesc(companyName),companyName + " Firmasının ilanları listelendi");
	}
	
	@Override
	public DataResult<List<JobAdvertDto>> getJobAdvertDto() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getJobAdvertDto());
	}
	
	@Override
	public DataResult<List<JobAdvertDto>> getJobAdvertDtoActiveAdvertsByDate() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getJobAdvertDtoActiveAdvertsByDate());
	}
	
	private Result CheckIfRepetition(JobAdvert jobAdvert) {
		if(jobAdvertDao.getAllByActivityStatusIsTrueAndPosition_PositionId(jobAdvert.getPosition().getPositionId()).stream().count() != 0) {
			return new ErrorResult("Bu pozisyonunda aktif iş ilanınız bulunmaktadır.");
		}
		return new SuccessResult();
	}

	

	

	

	

	
	
}

