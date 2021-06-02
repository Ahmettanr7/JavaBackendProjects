package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.AbilityService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EducationService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EmailVerificationService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployeeService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.ExperienceService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.LanguageService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.SingleInformationService;
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
import AhmetTanrikulu.HRMSBackend.entities.dtos.CurriculumVitaeDto;

@Service
public class EmployeeManager implements EmployeeService{
	
	private EmployeeDao employeeDao;
	private UserService userService;
	private EmailVerificationService emailVerificationService;
	private AbilityService abilityService;
	private SingleInformationService singleInformationService;
	private EducationService educationService;
	private ExperienceService experienceService;
	private LanguageService LanguageService;

	@Autowired
	public EmployeeManager(
			EmployeeDao employeeDao,
			UserService userService, 
			EmailVerificationService emailVerificationService,
			AbilityService abilityService,
			SingleInformationService singleInformationService,
			EducationService educationService,
			ExperienceService experienceService,
			LanguageService languageService
			) {
		super();
		this.employeeDao = employeeDao;
		this.userService = userService;
		this.emailVerificationService = emailVerificationService; 
		this.abilityService = abilityService;
		this.singleInformationService = singleInformationService;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.LanguageService = languageService;
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Verier listelendi");
				
	}

	@Override
	public Result add(Employee employee) {
		var result = BusinessRules.run(
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
		this.emailVerificationService.generateCode(new EmailVerification(),savedUser.getUserId());
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

	@Override
	public DataResult<CurriculumVitaeDto> getCurriculumVitaeByUserId(int userId) {
		CurriculumVitaeDto cv = new CurriculumVitaeDto();

		cv.educations = this.educationService.getAllByUserIdOrderByGraduationDateDesc(userId).getData();
		cv.experiences = this.experienceService.getAllByUserIdOrderByQuitDate(userId).getData();
		cv.abilities = this.abilityService.getAllByUserId(userId).getData();
		cv.languages = this.LanguageService.getAllByUserId(userId).getData();
		cv.singleInformation = this.singleInformationService.getByUserId(userId).getData();
		
		return new SuccessDataResult<CurriculumVitaeDto>(cv);
		
	}

}
