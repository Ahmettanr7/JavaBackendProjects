package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.PositionService;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Position;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {
	
	private PositionService positionService;

	@Autowired
	public PositionsController(PositionService positionService) {
		super();
		this.positionService = positionService;
	}
	
	@GetMapping("/getall")
	public List<Position> getAll(){
		return this.positionService.getAll();
	}
	

}
