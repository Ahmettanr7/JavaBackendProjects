package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="system_employee")
@AllArgsConstructor
@NoArgsConstructor
public class SystemEmployee extends User{
	
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="last_name", nullable = false)
	private String lastName;
	
	@Column(name="date_of_start", nullable = false)
	private Date dateOfStart;
	
	@Column(name="phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name="position_id", nullable = false)
	private int positionId;
}


