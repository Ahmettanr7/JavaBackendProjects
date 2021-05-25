package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
//import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
//import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
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
	public User add(User user) {
		return this.userDao.save(user);
	}

	@Override
	public User update(User user) {
		return this.userDao.save(user);
		
	}
	

	//private Result checkIfInfoIsNull(User user) {
	//	if (user.getEmail().isBlank() || user.getPassword().isBlank()) {
	//		return new ErrorResult("Lütfen tüm alanları doldurun");
	//	} else {
	//		return new SuccessResult();
	//	}
	//}
	
	//private Result CheckIfTheEmailIsRegistered(User user) {
	//	if(userDao.findAllByEmail(user.getEmail()).stream().count() != 0) {
	//		return new ErrorResult("'" + user.getEmail() + "'" +" adresiyle daha önce hesap açılmış");
	//	}
	//	return new SuccessResult();
	//}
	
	//private Result isRealEmail(User user) {
	//	 String regex = "^(.+)@(.+)$";
	//    Pattern pattern = Pattern.compile(regex);
	//    Matcher matcher = pattern.matcher(user.getEmail());
	//    if(!matcher.matches()) {
	//    	 return new ErrorResult("Hatalı Email adresi girdiniz");
	//     }
	//     return new SuccessResult();
	//     }

}
