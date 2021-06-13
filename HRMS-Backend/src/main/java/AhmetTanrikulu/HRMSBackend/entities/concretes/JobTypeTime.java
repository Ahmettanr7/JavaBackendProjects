package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="job_type_time")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
public class JobTypeTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="time_type_id")
	private int timeTypeId;
	
	@Column(name="time_type_name")
	private String timeTypeName;

	@OneToMany(mappedBy = "jobTypeTime")
	private List<JobAdvert> jobAdverts;
}