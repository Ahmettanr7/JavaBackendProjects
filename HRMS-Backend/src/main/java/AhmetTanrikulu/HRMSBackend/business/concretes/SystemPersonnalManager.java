package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.SystemPersonnalService;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.SystemPersonnalDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.SystemPersonnal;

@Service
public class SystemPersonnalManager implements SystemPersonnalService{
	
	private SystemPersonnalDao systemPersonalDao;

	@Autowired
	public SystemPersonnalManager(SystemPersonnalDao systemPersonalDao) {
		super();
		this.systemPersonalDao = systemPersonalDao;
	}

	@Override
	public List<SystemPersonnal> getAll() {
		return this.systemPersonalDao.findAll();
	}

	@Override
	public SystemPersonnal getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(SystemPersonnal systemPersonnal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SystemPersonnal systemPersonnal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SystemPersonnal systemPersonnal) {
		// TODO Auto-generated method stub
		
	}

}
