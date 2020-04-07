package com.techelevator.npgeek.model.user;

import java.util.List;

public interface UserDAO {

	public User saveUser(String userName, String password, String email, String passwordHint);
	
	public void changePassword(User user, String newPassword);
	
	public User getValidUserWithPassword(String userName, String password);
	
	public List<User> getAllUsers();
	
	public void changeTemperaturePreference(User user, String temperature);
}
