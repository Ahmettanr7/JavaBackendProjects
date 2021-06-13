package AhmetTanrikulu.HRMSBackend.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import AhmetTanrikulu.HRMSBackend.business.abstracts.JobTypeService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.SuccessDataResult;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.JobTypePlaceDao;
import AhmetTanrikulu.HRMSBackend.dataAccess.abstracts.JobTypeTimeDao;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypePlace;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypeTime;

@Service
public class JobTypeManager implements JobTypeService{
	
	private JobTypePlaceDao jobTypePlaceDao;
	private JobTypeTimeDao jobTypeTimeDao;

	public JobTypeManager(JobTypePlaceDao jobTypePlaceDao, JobTypeTimeDao jobTypeTimeDao) {
		super();
		this.jobTypePlaceDao = jobTypePlaceDao;
		this.jobTypeTimeDao = jobTypeTimeDao;
	}

	@Override
	public DataResult<List<JobTypeTime>> getAllTime() {
		return new SuccessDataResult<List<JobTypeTime>>(this.jobTypeTimeDao.findAll());
	}

	@Override
	public DataResult<List<JobTypePlace>> getAllPlace() {
		return new SuccessDataResult<List<JobTypePlace>>(this.jobTypePlaceDao.findAll());
	}

}
