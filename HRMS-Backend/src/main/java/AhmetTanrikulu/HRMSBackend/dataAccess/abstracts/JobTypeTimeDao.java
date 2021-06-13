package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypeTime;

public interface JobTypeTimeDao extends JpaRepository<JobTypeTime, Integer>{

}
