package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public DataResult<Optional<SystemEmployee>> getByUserId(int userId) {
		return new SuccessDataResult <Optional<SystemEmployee>>(this.systemEmployeeDao.findById(userId),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(SystemEmployee systemEmployee) {
		var result = BusinessRules.run(
				checkIfInfoIsNull(systemEmployee),
				CheckIfTheEmailIsRegistered(systemEmployee),
				isRealEmail(systemEmployee)
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
	
	private Result checkIfInfoIsNull(SystemEmployee systemEmployee) {
		if (systemEmployee.getEmail().isBlank() ||systemEmployee.getPassword().isBlank() ||
			systemEmployee.getFirstName().isBlank() || systemEmployee.getLastName().isBlank() ||
			systemEmployee.getPhoneNumber().isBlank() || systemEmployee.getPhoneNumber().isBlank()) {
			return new ErrorResult("Lütfen tüm alanları doldurun");
		} else {
			return new SuccessResult();
		}
	}
	
	private Result CheckIfTheEmailIsRegistered(SystemEmployee systemEmployee) {
		if(systemEmployeeDao.findAllByEmail(systemEmployee.getEmail()).stream().count() != 0) {
			return new ErrorResult("'" + systemEmployee.getEmail() + "'" +" adresiyle daha önce hesap açılmış");
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(SystemEmployee systemEmployee) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(systemEmployee.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Hatalı Email adresi girdiniz");
	     }
	     return new SuccessResult();
	     }

	

}
