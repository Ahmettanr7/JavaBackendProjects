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
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee{
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="nationality_id")
	private String nationalityId;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="phone_number")
	private String phoneNumber;	
}