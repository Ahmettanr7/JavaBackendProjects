package AhmetTanrikulu.HRMSBackend.entities.dtos;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertDto {
	
	private int jobAdvertId;
	
	private String companyName;
	
	private String positionName;
	
	private int quantity;
	
	private LocalDate advertDate;
	
	private LocalDate dueDate;
	
	private boolean activityStatus;
	
	private String cityName;
	
	private String description;
	
	private float minSalary;
	
	private float maxSalary;
	

}
