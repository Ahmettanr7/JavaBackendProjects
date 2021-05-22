package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Position;

public interface PositionService {
	List<Position> getAll();
	Position getById(int id);
	void add(Position position);
	void update(Position position);
	void delete(Position position);

}
