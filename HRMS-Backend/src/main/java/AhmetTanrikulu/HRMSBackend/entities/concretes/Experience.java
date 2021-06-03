package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.time.LocalDate;
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
@Table(name = "experiences")
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int experienceId;
	
	@Column(name = "company_name")
	@NotBlank
	@NotNull
	private String companyName;
	
	@Column(name = "position")
	@NotBlank
	@NotNull
	private String position;

	@Column(name="date_of_start")
	@NotNull
	private Date dateOfStart;
	
	@Column(name="quit_date")
	private LocalDate quitDate;
	
	@Column(name="working_status_b")
	private boolean workingStatus_b ;
	
	@Column(name="working_status")
	private String workingStatus;
	
	@Column(name="reason_for_leaving")
	private String reasonForLeaving;
	
	@Column(name="user_id")
	@NotNull
	private int userId;
	
	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnore
	private Employee employee;
}
