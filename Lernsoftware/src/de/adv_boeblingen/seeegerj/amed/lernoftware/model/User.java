package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_user")
public class User {
	@Id
	@Column(name = "username")
	private String mUsername;

	@Column(name = "created")
	private long mCreated;

	@Column(name = "lastlogin")
	private long mLastLogin;

	@Column(name = "password")
	private String mPassword;

	public String getUsername() {
		return this.mUsername;
	}

	public void setUsername(String username) {
		this.mUsername = username;
	}

	public String getPassword() {
		return this.mPassword;
	}

	public void setPassword(String password) {
		this.mPassword = password;
	}

	public long getCreated() {
		return this.mCreated;
	}

	public void setCreated(long mCreated) {
		this.mCreated = mCreated;
	}

	public long getLastLogin() {
		return this.mLastLogin;
	}

	public void setLastLogin(long mLastLogin) {
		this.mLastLogin = mLastLogin;
	}

}
