package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.EmployeeService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.Result;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.EmployeeDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService{
	
	private EmployeeDao employeeDao;

	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),"Verier listelendi");
				
	}

	@Override
	public DataResult<Optional<Employee>> getByUserId(int id) {
		return new SuccessDataResult <Optional<Employee>>(this.employeeDao.findById(id),"Belirtilen id numarasına göre getirildi");
	}

	@Override
	public Result add(Employee employee) {
		this.employeeDao.save(employee);
		return new SuccessResult("İşçi eklendi");
		
	}

	@Override
	public Result update(Employee employee) {
		this.employeeDao.save(employee);
		return new SuccessResult("İşçi eklendi");
		
	}

	@Override
	public Result delete(Employee employee) {
		this.employeeDao.delete(employee);
		return new SuccessResult("İşçi silindi");
		
	}

}
