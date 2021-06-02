package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "educations")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int educationId;
	@Column(name = "university_name")
	@NotNull
	@NotBlank
	private String universityName;
	
	@Column(name = "department")
	@NotNull
	@NotBlank
	private String department;

	@Column(name = "starting_date")
	@NotNull
	private Date startingDate;
	
	@Column(name = "graduation_date")
	private Date graduationDate;
	
	@Column(name="graduation_status")
	private boolean graduationStatus;
	
	@Column(name="education_status")
	private String educationStatus;
	
	@Column(name="user_id")
	@NotNull
	private int userId;
	
	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnore
	private Employee employee;
}
