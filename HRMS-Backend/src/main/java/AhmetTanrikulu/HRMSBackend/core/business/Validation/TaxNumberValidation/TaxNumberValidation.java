package AhmetTanrikulu.HRMSBackend.core.business.Validation.TaxNumberValidation;


public class TaxNumberValidation {
	
	
	public static boolean isRealPerson(String taxNumber) {

		return TaxNumberFakeMernis.validate(taxNumber);
	}

}
