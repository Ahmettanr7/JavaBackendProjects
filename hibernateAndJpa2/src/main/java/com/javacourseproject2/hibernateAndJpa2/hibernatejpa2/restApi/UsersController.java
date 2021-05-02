package com.javacourseproject2.hibernateAndJpa2.hibernatejpa2.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javacourseproject2.hibernateAndJpa2.Entities.*;
import com.javacourseproject2.hibernateAndJpa2.Business.IUsersService;

@RestController
@RequestMapping("/api")
public class UsersController {
	private IUsersService usersService;
	
	@Autowired
	public UsersController(IUsersService usersService) {
		this.usersService = usersService;
	}
	
	@GetMapping("/users")
	public List<Users> get(){
		return usersService.GetAll();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Users users) {
		usersService.add(users);
	}
	
	@PostMapping("/update")
	public void update(@RequestBody Users users) {
		usersService.update(users);
	}
	
	@PostMapping("/delete")
	public void delete(@RequestBody Users users) {
		usersService.delete(users);
	}
	@GetMapping("/users/{id}")
	public Users getById(@PathVariable int id){
		return usersService.getById(id);
	}
}
