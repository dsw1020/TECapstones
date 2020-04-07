package com.techelevator.npgeek.model.user;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	
	private long id;
	
    @NotBlank(message = "Username is required")
	private String username;
	
    @NotBlank(message = "Password is required")
    private String password;
	private String confirmPassword;
	
	private boolean passwordMatching;
	private boolean emailMatching;
	
    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (password != null) {
            return password.equals(confirmPassword);
        }
        return true;
    }
    
    @NotBlank(message = "Password hint is required")
	private String passwordHint;
	
	@NotBlank(message="Verify email address is required")
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message="Please enter a valid e-mail address")
	private String email;
	private String confirmEmail;

	@AssertTrue(message = "Email must match")
    public boolean isEmailMatching() {
        if (email != null) {
            return email.equals(confirmEmail);
        }
        return true;
    }
	
	private LocalDateTime loginTime;
	
	private String lastLoginTime;
	
	private String preferredTemp;

	public long getId() {
		return id;
	}

	public void setId(long newId) {
		this.id = newId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setPasswordMatching(boolean passwordMatching) {
		this.passwordMatching = passwordMatching;
	}

	public String getPreferredTemp() {
		return preferredTemp;
	}

	public void setPreferredTemp(String preferredTemp) {
		this.preferredTemp = preferredTemp;
	}
	
    public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	
}
