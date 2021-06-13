package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypePlace;

public interface JobTypePlaceDao extends JpaRepository<JobTypePlace, Integer>{

}
