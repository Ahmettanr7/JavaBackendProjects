package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployerUpdateService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployerDao;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployerUpdateDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employer;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmployerUpdate;

@Service
public class EmployerUpdateManager implements EmployerUpdateService{
	
	private EmployerUpdateDao employerUpdateDao;
	private EmployerDao employerDao;

	public EmployerUpdateManager(
			EmployerUpdateDao employerUpdateDao,
			EmployerDao employerDao
				) {
		super();
		this.employerUpdateDao = employerUpdateDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByUserId(int userId) {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getByUserIdOrderByUpdateIdDesc(userId));
	}

	@Override
	public DataResult<List<EmployerUpdate>> getAllByApprovalStatusIsFalseOrderByUpdateDate() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getAllByApprovalStatusIsFalseOrderByUpdateDate());
	}

	@Override
	public Result add(EmployerUpdate employerUpdate) {
		var result = BusinessRules.run(
				CheckIfTheEmailIsRegistered(employerUpdate),
				isRealEmail(employerUpdate)
				);
		if (result != null) {
			return result;
		}
		
		LocalDate now = LocalDate.now();
		employerUpdate.setUpdateDate(now);
		employerUpdate.setApprovalStatus(false);
		employerUpdate.setUpdateStatus("Onay Bekliyor");
		this.employerUpdateDao.save(employerUpdate);
		return new SuccessResult("Admin kontrolünden sonra bilgileriniz güncellenecektir. Takip ediniz.");
	}
	
	@Override
	public Result confirm(int id) {
		EmployerUpdate update = this.employerUpdateDao.getByUpdateId(id);
		Employer employer = this.employerDao.getByUserId(update.getUserId());
		
		if(update.isApprovalStatus()== true) {
			return new ErrorResult ("Zaten Onaylanmış");
		}
		
			employer.setEmail(update.getEmail());
			employer.setPassword(update.getPassword());
			employer.setCompanyName(update.getCompanyName());
			employer.setWebSite(update.getWebSite());
			employer.setPhoneNumber(update.getPhoneNumber());
		
		update.setApprovalStatus(true);
		update.setUpdateStatus("Onaylandı");
		
		this.employerDao.save(employer);
		this.employerUpdateDao.save(update);
		
		return new SuccessResult("Güncelleme Onaylandı");
		
		
	}
	
	
	private Result CheckIfTheEmailIsRegistered(EmployerUpdate employerUpdate) {
		if(!employerUpdate.getEmail().isEmpty()) {
		if(employerDao.findAllByEmail(employerUpdate.getEmail()).stream().count() != 0) {
			return new ErrorResult("'" + employerUpdate.getEmail() + "'" +" adresiyle daha önce hesap açılmış");
		}
		}
		return new SuccessResult();
	}
	
	private Result isRealEmail(EmployerUpdate employerUpdate) {
		if(!employerUpdate.getEmail().isEmpty()) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employerUpdate.getEmail());
	     if(!matcher.matches()) {
	    	 return new ErrorResult("Hatalı Email adresi girdiniz");
	     }
		}
	     return new SuccessResult();
	     }

}
