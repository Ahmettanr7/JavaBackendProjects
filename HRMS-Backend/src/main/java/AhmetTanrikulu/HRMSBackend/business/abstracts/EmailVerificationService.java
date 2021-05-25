package AhmetTanrikulu.HRMSBackend.business.abstracts;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.EmailVerification;

public interface EmailVerificationService {
	void generateCode(EmailVerification code, Integer id);
	Result verify(String verificationCode, Integer id);
}