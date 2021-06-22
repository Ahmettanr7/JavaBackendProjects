package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.AbilityService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.AbilityDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Ability;

@Service
public class AbilityManager implements AbilityService{
	private AbilityDao abilityDao;

	@Autowired
	public AbilityManager(AbilityDao abilityDao) {
		super();
		this.abilityDao = abilityDao;
	}
	
	@Override
	public Result add(Ability ability) {
		 this.abilityDao.save(ability);
		 return new SuccessResult("Yetenek eklendi");
	}

	@Override
	public DataResult<List<Ability>> getAll() {
		return new SuccessDataResult<List<Ability>>(this.abilityDao.findAll(),"Yetenekler getirildi");
	}

	@Override
	public DataResult<List<Ability>> getAllByUserId(int user_id) {
		return new SuccessDataResult<List<Ability>>(this.abilityDao.getAllByUserId(user_id));
	}

	@Override
	public Result delete(int id) {
		this.abilityDao.deleteById(id);
		return new SuccessResult("Yetenek silindi");
	}

}
