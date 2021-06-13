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
@Table(name="job_type_place")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
public class JobTypePlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="place_type_id")
	private int placeTypeId;
	
	@Column(name="place_type_name")
	private String placeTypeName;

	@OneToMany(mappedBy = "jobTypePlace")
	private List<JobAdvert> jobAdverts;
}