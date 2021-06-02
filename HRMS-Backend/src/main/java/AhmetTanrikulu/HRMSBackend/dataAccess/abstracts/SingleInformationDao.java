package AhmetTanrikulu.HRMSBackend.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import AhmetTanrikulu.HRMSBackend.entities.concretes.SingleInformation;

public interface SingleInformationDao extends JpaRepository<SingleInformation, Integer>{
	SingleInformation getByUserId(int userId);

}
