package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Favorite;

public interface FavoriteService {
	Result add(Favorite favorite);
	
	Result delete(int id);
	
	DataResult<List<Favorite>> getAllByUserId (int userId);

}
