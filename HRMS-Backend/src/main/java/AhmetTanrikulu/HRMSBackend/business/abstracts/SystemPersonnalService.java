package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;


public interface SystemPersonnalService {
	List<SystemPersonnal> getAll();
	SystemPersonnal getById(int id);
	void add(SystemPersonnal systemPersonnal);
	void update(SystemPersonnal systemPersonnal);
	void delete(SystemPersonnal systemPersonnal);

}
