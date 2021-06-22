package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmailVerificationService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployerService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.business.Validation.TaxNumberValidation.TaxNumberValidation;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployerDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmailVerification;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employer;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;
	private UserService userService;
	private EmailVerificationService emailVerificationService;

	@Autowired
	public EmployerManager(
			EmployerDao employerDao,
			UserService userService,
			EmailVerificationService emailVerificationService
			) {
		super();
		this.employerDao = employerDao;
		this.userService = userService;
		this.emailVerificationService =  emailVerificationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Veriler listelendi");
		
	}

	@Override
	public DataResult<Employer> getByUserId(int userId) {
		return new SuccessDataResult <Employer>(this.employerDao.getByUserId(userId),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(Employer employer) {
		var result = BusinessRules.run(
				CheckIfTheEmailIsRegistered(employer),
				isRealEmail(employer),
				CheckIfTheTaxNumberIsRegistered(employer),
				NationalityIdValidation(employer),
				isRealPhoneNumber(employer)
				);
		if (result != null) {
			return result;
		}
		User savedUser = this.userService.add(employer);
		this.emailVerificationService.generateCode(new EmailVerification(),savedUser.getUserId());
		LocalDate now = LocalDate.now();
		employer.setCreationDate(now);
		this.employerDao.save(employer);
		return new SuccessResult("İş veren olarak kayıt olundu ,lütfen hesabınızı email adresinize"
				+ " gönderdiğimiz kod ile doğrulayınız ID numaranız:"+employer.getUserId());
		
	}

	@Override
	public Result update(Employer employer) {
		this.employerDao.save(employer);
		return new SuccessResult("İşveren güncellendi");
		
	}

	@Override
	public Result delete(Employer employer) {
		this.employerDao.delete(employer);
		return new SuccessResult("İşveren silindi");
		
	}
	
	private Result CheckIfTheEmailIsRegistered(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			return new ErrorResult("'" + employer.getEmail() + "'" +" adresiyle daha önce hesap açılmış");
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Hatalı Email adresi girdiniz");
	     }
	     return new SuccessResult();
	     }
	
	private Result CheckIfTheTaxNumberIsRegistered(Employer employer) {
		if(employerDao.findAllByTaxNumber(employer.getTaxNumber()).stream().count() != 0) {
			return new ErrorResult("'" + employer.getTaxNumber() + "'" +" kimlik numarasıyla daha önce hesap açılmış. Tekrar hesap açamazsınız.");
		}
		return new SuccessResult();
	}
	
	private Result NationalityIdValidation(Employer employer) {
		if(!TaxNumberValidation.isRealPerson(employer.getTaxNumber())) {
			return new ErrorResult("Vergi numarası doğrulanamadı");
		}
		return new SuccessResult();
	}
	
	private Result isRealPhoneNumber(Employer employer) {
		String phoneNumber = employer.getPhoneNumber();
		String regex = "^[0-9]+$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(phoneNumber);
	     if(!matcher.matches() || phoneNumber.length()<10) {
	    	 return new ErrorResult("Hatalı telefon numarası girdiniz");
	     }
	     return new SuccessResult();
	     }
	}
	
	


