package com.javacourseproject2.hibernateAndJpa2.DataAccess;

import java.util.List;
import com.javacourseproject2.hibernateAndJpa2.Entities.*;

public interface IUsersDal {
	List<Users> GetAll();
	void add(Users users);
	void update(Users users);
	void delete(Users users);
	Users getById(int id);
}
