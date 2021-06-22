package AhmetTanrikulu.HRMSBackend.api.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AhmetTanrikulu.HRMSBackend.business.abstracts.FavoriteService;
import AhmetTanrikulu.HRMSBackend.core.utilities.results.DataResult;
import AhmetTanrikulu.HRMSBackend.entities.concretes.Favorite;

@RestController
@RequestMapping("/api/favorites/")
@CrossOrigin
public class FavoritesController {
	
	private FavoriteService favoriteService;

	@Autowired
	public FavoritesController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	};
	
	@GetMapping("getbyuserid")
	public DataResult<List<Favorite>> getByUserId(int userId){
		return this.favoriteService.getAllByUserId(userId);
	}
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody Favorite favorite) {
		return ResponseEntity.ok(this.favoriteService.add(favorite));
	}
	
	@PostMapping("delete")
	public ResponseEntity<?> deleteById(@RequestParam int id) {
		return ResponseEntity.ok(this.favoriteService.delete(id));
	}

}
