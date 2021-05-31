package AhmetTanrikulu.HRMSBackend.entities.concretes;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User{
	

	@Column(name="first_name")
	@NotNull
	@NotBlank
	private String firstName;
	
	@Column(name="last_name")
	@NotNull
	@NotBlank
	private String lastName;
	
	@Column(name="nationality_id")
	@NotNull
	@NotBlank
	private String nationalityId;
	
	@Column(name="birth_date")
	@NotNull
	@NotBlank
	private Date birthDate;
	
	@Column(name="phone_number")
	@NotNull
	@NotBlank
	private String phoneNumber;	
}