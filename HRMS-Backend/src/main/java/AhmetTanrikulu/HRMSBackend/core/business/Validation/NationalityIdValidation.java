package AhmetTanrikulu.HRMSBackend.core.business.Validation;

public class NationalityIdValidation {
	
	
	public static boolean isRealPerson(String nationalityId) {	
		return FakeMernis.validate(nationalityId);
	}

}
