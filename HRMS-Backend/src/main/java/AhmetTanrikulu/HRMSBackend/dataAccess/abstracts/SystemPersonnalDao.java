package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;

public interface SystemPersonnalDao extends JpaRepository<SystemPersonnal, Integer> {

}