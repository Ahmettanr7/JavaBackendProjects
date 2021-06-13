package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
				CheckIfSalary(jobAdvert)
				);
		if (result != null) {
			return result;
		}
		LocalDate now = LocalDate.now();
		jobAdvert.setAdvertDate(now);
		jobAdvert.setActivityStatus(false); //Sistem çalışanı onayı gerekli!
		jobAdvert.setAdStatusDescription("ONAY BEKLİYOR");
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan onaylandıktan sonra yayınlanacak");	
	}
	
	@Override
	public Result closeAdvert(int jobAdvertId) {
		var jobAdvert = this.jobAdvertDao.getByJobAdvertId(jobAdvertId);
		if(jobAdvert.isActivityStatus()==true) {
			jobAdvert.setAdStatusDescription("İŞVEREN TARAFINDAN KALDIRILDI");
			jobAdvert.setActivityStatus(false);
			this.jobAdvertDao.save(jobAdvert);
			return new SuccessResult("İlan yayından kaldırıldı");
			}else if(jobAdvert.isActivityStatus()==false) {
				return new ErrorResult("İlan zaten yayında değil");	
			}
				return new ErrorResult("Hata");	
	}
	
	@Override
	public Result closeAdvertAdmin(int jobAdvertId) {
		var jobAdvert = this.jobAdvertDao.getByJobAdvertId(jobAdvertId);
		if(jobAdvert.isActivityStatus()==true) {
			jobAdvert.setAdStatusDescription("ADMİN TARAFINDAN KALDIRILDI");
			jobAdvert.setActivityStatus(false);
			this.jobAdvertDao.save(jobAdvert);
			return new SuccessResult("İlan yayından kaldırıldı");
			}else if(jobAdvert.isActivityStatus()==false) {
				return new ErrorResult("İlan zaten yayında değil");	
			}
				return new ErrorResult("Hata");	
	}
	
	@Override
	public Result confirmAdvert(int jobAdvertId) {
		var jobAdvert = this.jobAdvertDao.getByJobAdvertId(jobAdvertId);
		if(jobAdvert.isActivityStatus()==true) {
			return new ErrorResult ("İlan zaten yayında");
		}
		jobAdvert.setAdStatusDescription("YAYINDA");
		jobAdvert.setActivityStatus(true);
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İlan onaylandı");
	}
	
	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		Sort sort =Sort.by(Sort.Direction.DESC,"advertDate");
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(sort),"İlanlar tablosunun bütün verileri listelendi");
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
	public DataResult<List<JobAdvert>> getAllByCity_CityName(String cityName) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByCity_CityName(cityName));
	}

	@Override
	public DataResult<List<JobAdvert>> getAllByCity_CityId(int cityId) {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllByCity_CityId(cityId));
	}	
	
	@Override
	public DataResult<List<JobAdvertDto>> getJobAdvertDto() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getJobAdvertDto());
	}
	
	@Override
	public DataResult<List<JobAdvertDto>> getJobAdvertDtoActiveAdvertsByDate() {
		return new SuccessDataResult<List<JobAdvertDto>>(this.jobAdvertDao.getJobAdvertDtoActiveAdvertsByDate());
	}
	
	@Override
	public DataResult<JobAdvert> getByJobAdvertId(int jobAdvertId) {
		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getByJobAdvertIdAndActive(jobAdvertId));
	}
	
	@Override
	public DataResult<List<JobAdvert>> getByActivityStatusIsFalseOrderByAdvertDateDesc() {
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByActivityStatusIsFalseOrderByAdvertDateDesc());
	}
	
//	private Result CheckIfRepetition(JobAdvert jobAdvert) {
//		if(jobAdvertDao.getAllByActivityStatusIsTrueAndPosition_PositionId(jobAdvert.getPosition().getPositionId()).stream().count() != 0) {
//			return new ErrorResult("Bu pozisyonunda aktif iş ilanınız bulunmaktadır.");
//		}
//		return new SuccessResult();
//	}
	private Result CheckIfSalary(JobAdvert jobAdvert) {
		if(jobAdvert.getMinSalary() > jobAdvert.getMaxSalary()) {
			return new ErrorResult("Minimum maaş Maximum maaştan yüksek olamaz!");
		}
		return new SuccessResult();
	}

}

