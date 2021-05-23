package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.UserDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	private UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(),"Veriler listelendi");
	}

	@Override
	public DataResult <Optional<User>> getById(int id) {
		return new SuccessDataResult <Optional<User>>(this.userDao.findById(id),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi");
		
	}

	@Override
	public Result update(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı güncellendi");
		
	}

	@Override
	public Result delete(User user) {
		this.userDao.delete(user);
		return new SuccessResult("Kullanıcı silindi");
		
	}

}
