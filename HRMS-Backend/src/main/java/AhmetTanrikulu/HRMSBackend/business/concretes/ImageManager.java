package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.ImageService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.ImageDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Image;



@Service
@Transactional
public class ImageManager implements ImageService {

	ImageDao imageDao;
	
	@Autowired
	public ImageManager(ImageDao imageDao) {
		super();
		this.imageDao = imageDao;
	}

	@Override
	public DataResult<List<Image>> getAll() {
		
	return new SuccessDataResult<List<Image>>(this.imageDao.findAll(),"Başarıyla listelendi");
	}

	@Override
	public Result add(Image image) {
		Date now=java.util.Calendar.getInstance().getTime();
		image.setDateOfCreation(now);
		this.imageDao.save(image);
		return new SuccessResult("Başarıyla eklendi");
	}

	@Override
	public DataResult<Image> getByUserId(int userId) {
		return new SuccessDataResult<Image>(this.imageDao.getByUser_UserId(userId));
	}

}
