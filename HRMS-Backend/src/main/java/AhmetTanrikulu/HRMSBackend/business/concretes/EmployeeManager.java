package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmailVerificationService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployeeService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.business.Validation.NationalityIdValidation;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployeeDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmailVerification;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

@Service
public class EmployeeManager implements EmployeeService{
	
	private EmployeeDao employeeDao;
	private UserService userService;
	private EmailVerificationService emailVerificationService;

	@Autowired
	public EmployeeManager(
			EmployeeDao employeeDao,
			UserService userService, 
			EmailVerificationService emailVerificationService) {
		super();
		this.employeeDao = employeeDao;
		this.userService = userService;
		this.emailVerificationService = emailVerificationService; 
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Verier listelendi");
				
	}

	@Override
	public DataResult<Optional<Employee>> getByUserId(int id) {
		return new SuccessDataResult <Optional<Employee>>(this.employeeDao.findById(id),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(Employee employee) {
		var result = BusinessRules.run(
				checkIfInfoIsNull(employee),
				CheckIfTheNationalityIdIsRegistered(employee),
				CheckIfTheEmailIsRegistered(employee),
				isRealEmail(employee),
				NationalityIdValidation(employee)
				);
		if (result != null) {
			return result;
		}
		User savedUser = this.userService.add(employee);
		this.employeeDao.save(employee);
		this.emailVerificationService.generateCode(new EmailVerification(),savedUser.getId());
		return new SuccessResult("İş arayan olarak kayıt olundu ,lütfen hesabınızı email adresinize"
				+ " gönderdiğimiz kod ile doğrulayınız");
		
	}

	@Override
	public Result update(Employee employee) {
		this.employeeDao.save(employee);
		return new SuccessResult("İşçi eklendi");
		
	}

	@Override
	public Result delete(Employee employee) {
		this.employeeDao.delete(employee);
		return new SuccessResult("İşçi silindi");
		
	}
	
	private Result checkIfInfoIsNull(Employee employee) {
		if (employee.getFirstName().isBlank() || employee.getLastName().isBlank()|| employee.getPhoneNumber().isBlank()||
				employee.getNationalityId().isBlank() || employee.getBirthDate()==null)
			{
			return new ErrorResult("Lütfen tüm alanları doldurun");
		}
		return new SuccessResult();
	}
	
	private Result CheckIfTheNationalityIdIsRegistered(Employee employee) {
		if(employeeDao.findAllByNationalityId(employee.getNationalityId()).stream().count() != 0) {
			return new ErrorResult("'" + employee.getNationalityId() + "'" +" kimlik numarasıyla daha önce hesap açılmış. Tekrar hesap açamazsınız.");
		}
		return new SuccessResult();
	}
	
	private Result CheckIfTheEmailIsRegistered(Employee employee) {
		if(employeeDao.findAllByEmail(employee.getEmail()).stream().count() != 0) {
			return new ErrorResult("'" + employee.getEmail() + "'" +" adresiyle daha önce hesap açılmış");
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(Employee employee) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employee.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Hatalı Email adresi girdiniz");
	     }
	     return new SuccessResult();
	     }
	
	private Result NationalityIdValidation(Employee employee) {
		if(!NationalityIdValidation.isRealPerson(employee.getNationalityId())) {
			return new ErrorResult("Kimlik doğrulanamadı");
		}
		return new SuccessResult();
	}

}
