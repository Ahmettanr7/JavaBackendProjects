package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;
import java.util.Optional;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Position;

public interface PositionService {
	DataResult<List<Position>> getAll();
	DataResult<Optional<Position>> getById(int id);
	Result add(Position position);
	Result update(Position position);
	Result delete(Position position);

}
