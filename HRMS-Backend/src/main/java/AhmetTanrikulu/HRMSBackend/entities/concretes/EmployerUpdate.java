package AhmetTanrikulu.HRMSBackend.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employer_updates")
@AllArgsConstructor
@NoArgsConstructor
public class EmployerUpdate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int updateId;
	

	@Column(name="user_id")
	@NotNull
	private int userId;
	
	@Column(name="email")
	@Email
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="imageUrl")
	private String imageUrl;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_site")
	private String webSite;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="update_date")
	private LocalDate updateDate;
	
	@Column(name="approval_status")
	private boolean approvalStatus;
	
	@Column(name="update_status")
	private String updateStatus;

}
