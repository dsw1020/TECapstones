package com.techelevator.npgeek.model.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.authentication.PasswordHasher;

@Component
public class JdbcUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JdbcUserDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}

	@Override
	public User saveUser(String userName, String password, String email, String passwordHint) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		long newId = jdbcTemplate.queryForObject(
				"INSERT INTO users(username, password, salt, email, password_hint, last_login) VALUES (?, ?, ?, ?, ?, ?) RETURNING id",
				Long.class, userName, hashedPassword, saltString, email, passwordHint, LocalDateTime.now());

		User newUser = new User();
		newUser.setId(newId);
		newUser.setUsername(userName);
		newUser.setEmail(email);
		newUser.setLoginTime(LocalDateTime.now());
		newUser.setPasswordHint(passwordHint);

		return newUser;

	}

	@Override
	public void changePassword(User user, String newPassword) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(newPassword, salt);
		String saltString = new String(Base64.encode(salt));

		jdbcTemplate.update("UPDATE users SET password=?, salt=? WHERE id=?", hashedPassword, saltString, user.getId());
	}

	@Override
	public User getValidUserWithPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * FROM users WHERE UPPER(username) = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if (results.next()) {
			String storedSalt = results.getString("salt");
			String storedPassword = results.getString("password");
			String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
			if (storedPassword.equals(hashedPassword)) {
				return mapResultToUser(results);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sqlSelectAllUsers = "SELECT id, username, email, password_hint, last_login FROM users";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

		while (results.next()) {
			User user = mapResultToUser(results);
			users.add(user);
		}

		return users;
	}

	@Override
	public void changeTemperaturePreference(User user, String temperature) {
		jdbcTemplate.update("UPDATE users set temperature_preference=? WHERE id=?", temperature, user.getId());
	}

	private User mapResultToUser(SqlRowSet results) {
		User user = new User();
		user.setId(results.getLong("id"));
		user.setUsername(results.getString("username"));
		user.setEmail(results.getString("email"));
		user.setPasswordHint(results.getString("password_hint"));
		user.setLoginTime(results.getTimestamp("last_login").toLocalDateTime());
		return user;
	}
}
