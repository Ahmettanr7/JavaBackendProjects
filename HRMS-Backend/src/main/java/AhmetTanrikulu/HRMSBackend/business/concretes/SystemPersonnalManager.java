package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SystemPersonnalService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.SystemPersonnalDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;

@Service
public class SystemPersonnalManager implements SystemPersonnalService{
	
	private SystemPersonnalDao systemPersonalDao;

	@Autowired
	public SystemPersonnalManager(SystemPersonnalDao systemPersonalDao) {
		super();
		this.systemPersonalDao = systemPersonalDao;
	}

	@Override
	public DataResult<List<SystemPersonnal>> getAll() {
		return new SuccessDataResult<List<SystemPersonnal>>(this.systemPersonalDao.findAll(),"Veriler listelendi");
	}

	@Override
	public DataResult<Optional<SystemPersonnal>> getByUserId(int userId) {
		return new SuccessDataResult <Optional<SystemPersonnal>>(this.systemPersonalDao.findById(userId),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(SystemPersonnal systemPersonnal) {
		this.systemPersonalDao.save(systemPersonnal);
		return new SuccessResult("Sistem çalışanı eklendi");
		
	}

	@Override
	public Result update(SystemPersonnal systemPersonnal) {
		this.systemPersonalDao.save(systemPersonnal);
		return new SuccessResult("Sistem çalışanı güncellendi");
	}

	@Override
	public Result delete(SystemPersonnal systemPersonnal) {
		this.systemPersonalDao.delete(systemPersonnal);
		return new SuccessResult("Sistem çalışanı silindi");
		
	}

}
