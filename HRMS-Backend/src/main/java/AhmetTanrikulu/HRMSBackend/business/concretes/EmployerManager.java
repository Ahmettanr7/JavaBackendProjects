package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployerService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployerDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Veriler listelendi");
		
	}

	@Override
	public DataResult<Optional<Employer>> getByUserId(int id) {
		return new SuccessDataResult <Optional<Employer>>(this.employerDao.findById(id),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(Employer employer) {
		this.employerDao.save(employer);
		return new SuccessResult("İşveren eklendi");
		
	}

	@Override
	public Result update(Employer employer) {
		this.employerDao.save(employer);
		return new SuccessResult("İşveren eklendi");
		
	}

	@Override
	public Result delete(Employer employer) {
		this.employerDao.delete(employer);
		return new SuccessResult("İşveren silindi");
		
	}
	
	

}
