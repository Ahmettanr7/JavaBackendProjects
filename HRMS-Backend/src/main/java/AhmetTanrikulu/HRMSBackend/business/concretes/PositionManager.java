package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.PositionService;
import AhmetTanrikulu.HRMSBackend.core.business.BusinessRules;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.ErrorResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.PositionDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Position;

@Service
public class PositionManager implements PositionService{
	private PositionDao positionDao;

	@Autowired
	public PositionManager(PositionDao positionDao) {
		super();
		this.positionDao = positionDao;
	}

	@Override
	public DataResult<List<Position>> getAll() {
		return new SuccessDataResult<List<Position>>(this.positionDao.findAll(),"Veriler listelendi");
				
	}

	@Override
	public DataResult <Optional<Position>> getById(int id) {
		return new SuccessDataResult <Optional<Position>>(this.positionDao.findById(id),"Belirtilen id numarasına göre getirildi");
				
	}

	@Override
	public Result add(Position position) {
		var result = BusinessRules.run(
				checkIfInfoIsNull(position),
				CheckIfThePositionName(position)
				);
		if (result != null) {
			return result;
		}
		this.positionDao.save(position);
		return new SuccessResult("Pozisyon eklendi");
		
	}

	@Override
	public Result update(Position position) {
		this.positionDao.save(position);
		return new SuccessResult("Pozisyon eklendi");
		
	}

	@Override
	public Result delete(Position position) {
		this.positionDao.delete(position);
		return new SuccessResult("Pozisyon silindi");
		
	}
	
	private Result checkIfInfoIsNull(Position position) {
		if (position.getPositionName().isBlank()) {
			return new ErrorResult("Lütfen tüm alanları doldurun");
		} else {
			return new SuccessResult();
		}
	}
	
	private Result CheckIfThePositionName(Position position) {
		if(positionDao.findAllByPositionName(position.getPositionName()).stream().count() != 0) {
			return new ErrorResult("'" + position.getPositionName() + "'" +" Bu departman daha önce eklenmiş.");
		}
		return new SuccessResult();
	}

}
