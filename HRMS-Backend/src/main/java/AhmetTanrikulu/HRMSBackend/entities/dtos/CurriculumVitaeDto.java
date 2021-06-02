package AhmetTanrikulu.HRMSBackend.entities.dtos;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Ability;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Education;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Experience;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Language;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDto {
	
	public Employee employee;

	public List<Education> educations;

	public List<Experience> experiences;
	
	public List<Ability> abilities;
		
	public List<Language> languages;
	
	public SingleInformation singleInformation;

	


}
