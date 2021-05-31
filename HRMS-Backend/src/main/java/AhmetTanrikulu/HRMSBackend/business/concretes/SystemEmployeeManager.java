package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SystemEmployeeService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.SystemEmployeeDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemEmployee;

@Service
public class SystemEmployeeManager implements SystemEmployeeService{
	
	private SystemEmployeeDao systemEmployeeDao;
	private UserService  userService;

	@Autowired
	public SystemEmployeeManager(SystemEmployeeDao systemEmployeeDao, UserService userService) {
		super();
		this.systemEmployeeDao = systemEmployeeDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<SystemEmployee>> getAll() {
		return new SuccessDataResult<List<SystemEmployee>>(this.systemEmployeeDao.findAll(),"Veriler listelendi");
	}
	
	@Override
	public DataResult<SystemEmployee> getByUserId(int userId) {
		return new SuccessDataResult<SystemEmployee>(this.systemEmployeeDao.getByUserId(userId));
	}

	@Override
	public DataResult<SystemEmployee> getByEmail(String email) {
		return new SuccessDataResult<SystemEmployee>(this.systemEmployeeDao.getByEmail(email));
	}

	@Override
	public Result add(SystemEmployee systemEmployee) {
		var result = BusinessRules.run(
				CheckIfTheEmailIsRegistered(systemEmployee)
				
				);
		if (result != null) {
			return result;
		}
		 this.userService.add(systemEmployee);
		this.systemEmployeeDao.save(systemEmployee);
		return new SuccessResult("Sistem çalışanı olarak kayıt olundu.");
		
	}

	@Override
	public Result update(SystemEmployee systemEmployee) {
		this.systemEmployeeDao.save(systemEmployee);
		return new SuccessResult("Sistem çalışanı güncellendi");
	}

	@Override
	public Result delete(SystemEmployee systemEmployee) {
		this.systemEmployeeDao.delete(systemEmployee);
		return new SuccessResult("Sistem çalışanı silindi");
		
	}
	
	private Result CheckIfTheEmailIsRegistered(SystemEmployee systemEmployee) {
		if(systemEmployeeDao.findAllByEmail(systemEmployee.getEmail()).stream().count() != 0) {
			return new ErrorResult("'" + systemEmployee.getEmail() + "'" +" adresiyle daha önce hesap açılmış. Lütfen giriş yapınız");
		}
		return new SuccessResult();
	}

	

	

}
