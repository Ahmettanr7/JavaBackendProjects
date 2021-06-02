package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer>{
	List<Education> getAllByUserIdOrderByGraduationDateDesc(int userId);

}
