package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Ability;

public interface AbilityDao extends JpaRepository<Ability, Integer>{
	List<Ability> getAllByUserId (int user_id);

}
