package com.tkpm.service;

import java.util.List;

import com.tkpm.dao.UserDAO;
import com.tkpm.entities.User;

//Using enum for applying Singleton Pattern
public enum UserService {

	INSTANCE;
	
	private UserDAO userDAO;
	
	private UserService() {
		userDAO = UserDAO.INSTANCE;
	}
	
	//Create new user
	public User createUser(User user) {
		return userDAO.create(user);
	}
	
	//Update an user
	public User updateUser(User user) {
		return userDAO.update(user);
	}
	
	//Delete an user by the given id
	public int deleteUser(Integer id) {
		return userDAO.delete(id);
	}
	
	//Find all users in database
	public List<User> findAllUsers() {
		
		return userDAO.findAll();
		
	}
	
	//Find user by id
	public User findUserById(Integer id) {
		return userDAO.find(id);
	}
	
	//Find user by username
	public User findUserByUsername(String username) {
		return userDAO.find(username);
	}
	
	//Authenticate
	public User login(User user) {
		
		//Find the user in the database by the username
		User other = this.findUserByUsername(user.getUsername());
		
		//Return null if the username is not existed
		if (null == other) {return null;}
		
		//Return null if the passwords are not matched
		if (!user.getEncryptedPassword().equals(other.getEncryptedPassword())) {return null;}
		
		//Return the user if ther username and password are matched
		return other;
		
	}
}
 