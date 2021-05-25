package AhmetTanrikulu.HRMSBackend.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmailVerificationService;
import AhmetTanrikulu.HRMSBackend.core.random.GenerateRandomCode;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmailVerificationDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmailVerification;
@Service
public class EmailVerificationManager  implements EmailVerificationService{
	private EmailVerificationDao emailVerificationDao;

    @Autowired
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		super();
		this.emailVerificationDao = emailVerificationDao;
	}

    @Override
	public void generateCode(EmailVerification code,Integer id) {
				
    	EmailVerification code_ = code;
				code_.setCode(null);
				code_.setVerified(false);
				if(code.isVerified() == false) {
					GenerateRandomCode generator = new GenerateRandomCode();
					String code_create = generator.create();
					code.setCode(code_create);
					code.setUserId(id);
			
					emailVerificationDao.save(code);
					
				}
				return ;
	}
	
	@Override
	public Result verify(String verificationCode,Integer id) {
		EmailVerification ref = emailVerificationDao.getById(id); //calismazsa getOne dene
		if(ref.getCode().equals(verificationCode)) {
			ref.setVerified(true);
			return  new SuccessDataResult<EmailVerification>(this.emailVerificationDao.save(ref),"Başarılı");
		}
		return  new ErrorDataResult<EmailVerification>(null,"Doğrulama Kodu Geçersiz");
		
		
		
		
		
		
	}


	

}