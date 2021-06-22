package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Favorite;

public interface FavoriteDao extends JpaRepository<Favorite, Integer>{
	List<Favorite> getAllByUserId (int user_id);
	List<Favorite> getAllByUserIdAndJobAdvertId(int userId,int jobAdvertId);
}
