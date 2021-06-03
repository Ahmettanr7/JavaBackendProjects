package AhmetTanrikulu.HRMSBackend.entities.concretes;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","singleInformations","abilities","educations","experiences","languages",})
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
	private Date birthDate;
	
	@Column(name="phone_number")
	@NotNull
	@NotBlank
	private String phoneNumber;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<SingleInformation> singleInformations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<Ability> abilities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<Education> educations;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<Experience> experiences;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<Language> languages;
 }