package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.entities.concretes.Employer;

public interface EmployerService {
	List<Employer> getAll();
	Employer getById(int id);
	void add(Employer employer);
	void update(Employer employer);
	void delete(Employer employer);

}