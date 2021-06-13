package AhmetTanrikulu.HRMSBackend.business.abstracts;

import java.util.List;

import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypePlace;
import AhmetTanrikulu.HRMSBackend.entities.concretes.JobTypeTime;

public interface JobTypeService {

	DataResult<List<JobTypeTime>> getAllTime();
	DataResult<List<JobTypePlace>> getAllPlace();
 }
