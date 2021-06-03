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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "single_informations")
@Entity
public class SingleInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int curriculumVitaeId;

	@Column(name = "github")
	@NotBlank
	@NotNull
	private String github;

	@Column(name = "linkedin")
	@NotBlank
	@NotNull
	private String linkedin;

	@Column(name = "cover_letter")
	private String coverLetter;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="user_id")
	@NotNull
	private int userId;

	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnore
	private Employee employee;
}
