package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Position;

public interface PositionDao extends JpaRepository<Position, Integer>{

}
