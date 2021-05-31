package AhmetTanrikulu.HRMSBackend.entities.dtos;

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
	
	private Date advertDate;
	
	private Date dueDate;
	
	private boolean activityStatus;
	
	

}
