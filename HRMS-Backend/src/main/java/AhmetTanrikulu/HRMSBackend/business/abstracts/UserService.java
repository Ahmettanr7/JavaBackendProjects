package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult <Optional<User>> getById(int id);
	User add(User user);
	User update(User user);

}
