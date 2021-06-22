package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer>{
	List<Experience> getAllByUserIdOrderByQuitDate(int userId);
	List<Experience> getAllByUserIdOrderByQuitDateAsc(int userId);

}
