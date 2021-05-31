package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	
	User add(User user);
	
	DataResult<User> findByEmail(String email);

}
