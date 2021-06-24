package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.time.LocalDate;
import java.util.List;
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
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

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
				CheckIfTheEmailIsRegistered(systemEmployee),
				isRealPhoneNumber(systemEmployee)
				);
		if (result != null) {
			return result;
		}
		User savedUser = this.userService.add(systemEmployee);
		 LocalDate now = LocalDate.now();
		 systemEmployee.setDateOfStart(now);
		this.systemEmployeeDao.save(systemEmployee);
		return new SuccessResult("Sistem çalışanı olarak kayıt olundu.");
		
	}
	
	@Override
	public Result update(SystemEmployee systemEmployee) {
		SystemEmployee sys = this.systemEmployeeDao.getByUserId(systemEmployee.getUserId());
		if(sys != null) {
			
		sys.setFirstName(systemEmployee.getFirstName());
			
		sys.setLastName(systemEmployee.getLastName());
			
		sys.setEmail(systemEmployee.getEmail());
			
		sys.setImageUrl(systemEmployee.getImageUrl());
			
		sys.setPhoneNumber(systemEmployee.getPhoneNumber());
			
		sys.setPassword(systemEmployee.getPassword());
			
			this.systemEmployeeDao.save(sys);
		return new SuccessResult("Güncelleme başarılı");
		}
		return new ErrorResult("Güncelleme başarısız");
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
	
	private Result isRealPhoneNumber(SystemEmployee systemEmployee) {
		String phoneNumber = systemEmployee.getPhoneNumber();
		String regex = "^[0-9]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(phoneNumber);
	     if(!matcher.matches() || phoneNumber.length()<10 || phoneNumber.length() > 12) {
	    	 return new ErrorResult("Hatalı telefon numarası girdiniz");
	     }
	     return new SuccessResult();
	     }

	

	

	

}
