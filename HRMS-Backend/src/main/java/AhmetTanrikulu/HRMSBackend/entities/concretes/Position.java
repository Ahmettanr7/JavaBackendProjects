package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="positions")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="position_id")
	private int positionId;
	
	@Column(name="position_name")
	@NotNull
	@NotBlank
	private String positionName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "position")
	private List<JobAdvert> jobAdverts;
	
	
	}