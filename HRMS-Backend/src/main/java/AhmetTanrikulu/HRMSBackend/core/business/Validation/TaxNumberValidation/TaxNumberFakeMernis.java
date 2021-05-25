package AhmetTanrikulu.HRMSBackend.core.business.Validation.TaxNumberValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaxNumberFakeMernis {
	public static boolean validate(String taxNumber) {

		 String regex = "^[0-9]+$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(taxNumber);
	     
		if(taxNumber.length() > 10) {
			return false;
		}
		
		else if(matcher.matches()) {
			return true;
		}
		
		
		
		else {
			return false;
		}
		
	}

}
