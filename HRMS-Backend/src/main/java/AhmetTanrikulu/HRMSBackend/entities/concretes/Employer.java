package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name="employers")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
public class Employer extends User{
	
	
	@Column(name="company_name", nullable = false)
	private String companyName;
	
	@Column(name="web_site", nullable = false)
	private String webSite;
	
	@Column(name="phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name="tax_number", nullable = false)
	private String taxNumber;
	
	@OneToMany(mappedBy = "employer")
	private List<JobAdvert> jobAdverts;
	
	
	}
