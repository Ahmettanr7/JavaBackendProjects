package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult <Optional<User>> getById(int id);
	Result add(User user);
	Result update(User user);
	Result delete(User user);

}
