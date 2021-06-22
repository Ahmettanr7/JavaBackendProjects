package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.ImageService;
import AhmetTanrikulu.HRMSBackend.business.abstracts.UserService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.ImageDao;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.UserDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Image;
import AhmetTanrikulu.HRMSBackend.entities.concretes.User;



@Service
@Transactional
public class ImageManager implements ImageService {

	ImageDao imageDao;
	UserDao userDao; 
	
	@Autowired
	public ImageManager(ImageDao imageDao, UserDao userDao) {
		super();
		this.imageDao = imageDao;
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<Image>> getAll() {
		
	return new SuccessDataResult<List<Image>>(this.imageDao.findAll(),"Başarıyla listelendi");
	}

	@Override
	public Result add(Image image) {
		LocalDate now = LocalDate.now();
		image.setDateOfCreation(now);
		this.imageDao.save(image);
//		var user = this.userDao.getByUserId(image.getUserId());
//		user.image.setUserId(image.getUserId());
		return new SuccessResult("Başarıyla eklendi");
	}

	@Override
	public DataResult<Image> getByUserId(int userId) {
		return new SuccessDataResult<Image>(this.imageDao.getByUserId(userId));
	}

}
