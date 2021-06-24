package AhmetTanrikulu.HRMSBackend.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Data
@Table(name = "images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name="name")
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="image_id")
	private String imageId;
	
	@Column(name="date_of_creation")
	private LocalDate dateOfCreation;
}