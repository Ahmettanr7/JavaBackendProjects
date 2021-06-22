package AhmetTanrikulu.HRMSBackend.entities.concretes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="favorites")
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	@NotNull
	private int userId;
	
	@Column(name="job_advert_id")
	@NotNull
	private int jobAdvertId;
	
	@ManyToOne()
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Employee employee;
	
	@ManyToOne()
	@JoinColumn(name = "job_advert_id", insertable = false, updatable = false)
	private JobAdvert jobAdvert;

}
