package com.javacourseproject2.hibernateAndJpa2.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.javacourseproject2.hibernateAndJpa2.DataAccess.IUsersDal;
import com.javacourseproject2.hibernateAndJpa2.Entities.Users;

@Service
public class UsersManager implements IUsersService {
	
	private IUsersDal usersDal;
	
	@Autowired
	public UsersManager(IUsersDal usersDal) {
		this.usersDal = usersDal;
	}

	@Override
	@Transactional
	public List<Users> GetAll() {
			
		return this.usersDal.GetAll();
	}

	@Override
	@Transactional
	public void add(Users users) {
		//İş kuralları yazılır
		//Kullanıcı veri tabanında yoksa ekle gibi...
		this.usersDal.add(users);
		
	}

	@Override
	@Transactional
	public void update(Users users) {
		this.usersDal.update(users);
		
	}

	@Override
	@Transactional
	public void delete(Users users) {
		this.usersDal.delete(users);
		
	}

	@Override
	@Transactional
	public Users getById(int id) {
		return this.usersDal.getById(id);
	}

}
