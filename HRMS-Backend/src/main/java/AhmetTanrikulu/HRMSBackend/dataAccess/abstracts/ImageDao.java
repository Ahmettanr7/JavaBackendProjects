package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer>{
	Image getByUser_UserId(int userId);

}
