package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer>{
	List<Image> getAllByUserId(int userId);

}