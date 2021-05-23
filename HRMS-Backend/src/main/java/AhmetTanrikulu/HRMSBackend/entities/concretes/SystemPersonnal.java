package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="system_personnals")
@AllArgsConstructor
@NoArgsConstructor
public class SystemPersonnal{
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_start")
	private Date dateOfStart;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="position_id")
	private int positionId;
	}
