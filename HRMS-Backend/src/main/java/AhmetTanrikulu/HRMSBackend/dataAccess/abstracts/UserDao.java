package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
