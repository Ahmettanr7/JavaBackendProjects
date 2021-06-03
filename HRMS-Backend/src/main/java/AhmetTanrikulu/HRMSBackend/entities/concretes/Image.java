package AhmetTanrikulu.HRMSBackend.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Data
@Table(name = "images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name="user_id")
	@NotNull
	private int userId;

    @Column(name="name")
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="image_id")
	private String imageId;
	
	@Column(name="date_of_creation")
	private Date dateOfCreation;
}