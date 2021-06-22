package AhmetTanrikulu.HRMSBackend.entities.concretes;


import java.time.LocalDate;
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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","singleInformations","abilities","educations","experiences","languages","favorites"})
@NoArgsConstructor
@AllArgsConstructor
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
	private LocalDate birthDate;
	
	@Column(name="phone_number")
	@NotNull
	@NotBlank
	private String phoneNumber;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<SingleInformation> singleInformations;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Ability> abilities;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Education> educations;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Experience> experiences;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Language> languages;
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Favorite> favorites;
	
	
 }