package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.FavoriteService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.FavoriteDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Favorite;

@Service
public class FavoriteManager implements FavoriteService{
	
	private FavoriteDao favoriteDao;
	
	@Autowired
	public FavoriteManager(FavoriteDao favoriteDao) {
		super();
		this.favoriteDao = favoriteDao;
	}

	@Override
	public Result add(Favorite favorite) {
		var result = BusinessRules.run(
				CheckIfAlreadyExists(favorite)
				);
		if (result != null) {
			return result;
		}
		this.favoriteDao.save(favorite);
		return new SuccessResult("İlan kaydedildi");
	}

	@Override
	public Result delete(int id) {
		this.favoriteDao.deleteById(id);
		return new SuccessResult("İlan kaydedilenlerden kaldırıldı");
	}

	@Override
	public DataResult<List<Favorite>> getAllByUserId(int userId) {
		return new SuccessDataResult<List<Favorite>>(this.favoriteDao.getAllByUserId(userId));
	}
	
	private Result CheckIfAlreadyExists(Favorite favorite){
		var query = favoriteDao.getAllByUserIdAndJobAdvertId(favorite.getUserId(), favorite.getJobAdvertId());
		if (query.stream().count() == 1) {
			return new ErrorResult("Daha önce kaydedilmiş");
		}
		return new SuccessResult();
	}

}
