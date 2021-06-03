package AhmetTanrikulu.HRMSBackend.entities.concretes;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name="job_adverts")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_advert_id")
	private int jobAdvertId;
	
	@Column(name="min_salary")
	private float minSalary;
	
	@Column(name="max_salary")
	private float maxSalary;
	
	@Column(name="quantity")
	@NotNull
	private int quantity;

	@Column(name="advert_date")
	private Date advertDate;
	
	@Column(name="due_date")
	@NotNull
	private Date dueDate;
	
	@Column(name="description")
	@NotNull
	@NotBlank
	private String description;
	
	@Column(name="activity_status")
	private boolean activityStatus;
	
	@ManyToOne()
	@JoinColumn(name = "position_id")
	@NotNull
	private Position position;
	
	@ManyToOne()
	@JoinColumn(name = "city_id")
	@NotNull
	private City city;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private Employer employer;

}
