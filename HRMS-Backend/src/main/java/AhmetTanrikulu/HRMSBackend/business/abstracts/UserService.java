package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface UserService {
	List<User> getAll();
	User getById(int id);
	void add(User user);
	void update(User user);
	void delete(User user);

}
