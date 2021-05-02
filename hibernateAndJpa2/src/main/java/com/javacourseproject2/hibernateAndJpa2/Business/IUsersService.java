package com.javacourseproject2.hibernateAndJpa2.Business;

import java.util.List;

import com.javacourseproject2.hibernateAndJpa2.Entities.Users;

public interface IUsersService {
	List<Users> GetAll();
	void add(Users users);
	void update(Users users);
	void delete(Users users);
	Users getById(int id);
}
