package AhmetTanrikulu.HRMSBackend.entities.concretes;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
	private LocalDate advertDate;
	
	@Column(name="due_date")
	@NotNull
	private LocalDate dueDate;
	
	@Column(name="description")
	@NotNull
	@NotBlank
	private String description;
	
	@Column(name="activity_status")
	private boolean activityStatus;
	
	@Column(name="ad_status_description")
	private String adStatusDescription;
	
	
	@ManyToOne()
	@JoinColumn(name = "position_id", insertable = false, updatable = false)
	private Position position;
	
	@ManyToOne()
	@JoinColumn(name = "city_id", insertable = false, updatable = false)
	private City city;
	
	@ManyToOne()
	@JoinColumn(name = "place_type_id", insertable = false, updatable = false)
	private JobTypePlace jobTypePlace;
	
	@ManyToOne()
	@JoinColumn(name = "time_type_id", insertable = false, updatable = false)
	private JobTypeTime jobTypeTime;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Employer employer;
	
	@Column(name="position_id")
	@NotNull
	private int positionId;
	
	@Column(name="city_id")
	@NotNull
	private int cityId;
	
	@Column(name="user_id")
	@NotNull
	private int userId;
	
	@Column(name="place_type_id")
	@NotNull
	private int placeTypeId;
	
	@Column(name="time_type_id")
	@NotNull
	private int timeTypeId;
	
//	@ManyToOne(targetEntity = Position.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "position_id", insertable = false, updatable = false)
//	@JsonIgnore
//	private Position position;
//	
//	@ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "city_id", insertable = false, updatable = false)
//	@JsonIgnore
//	private City city;
//	
//	@ManyToOne(targetEntity = JobTypePlace.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "place_type_id", insertable = false, updatable = false)
//	@JsonIgnore
//	private JobTypePlace jobTypePlace;
//	
//	@ManyToOne(targetEntity = JobTypeTime.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "time_type_id", insertable = false, updatable = false)
//	@JsonIgnore
//	private JobTypeTime jobTypeTime;
//	
//	@ManyToOne(targetEntity = Employer.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_id", insertable = false, updatable = false)
//	@JsonIgnore
//	private Employer employer;

}
