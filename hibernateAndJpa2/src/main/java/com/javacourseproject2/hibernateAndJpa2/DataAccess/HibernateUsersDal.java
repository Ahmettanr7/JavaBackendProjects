package com.javacourseproject2.hibernateAndJpa2.DataAccess;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacourseproject2.hibernateAndJpa2.Entities.Users;

@Repository
public class HibernateUsersDal implements IUsersDal {
	
	private EntityManager entityManager;
	
	@Autowired
	public HibernateUsersDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Users> GetAll() {
	Session session = entityManager.unwrap(Session.class);
	List<Users> users = session.createQuery("FROM Users", Users.class).getResultList();
	return users;
	}

	@Override
	public void add(Users users) {
		//if()
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(users);
	}

	@Override
	public void update(Users users) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(users);
		
	}

	@Override
	public void delete(Users users) {
		Session session = entityManager.unwrap(Session.class);
		Users usersToDelete = session.get(Users.class, users.getId());
		session.delete(usersToDelete);
	}

	@Override
	public Users getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Users users = session.get(Users.class, id);
		return users;
	}

}
