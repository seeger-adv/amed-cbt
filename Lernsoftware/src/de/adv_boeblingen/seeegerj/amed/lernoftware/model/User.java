package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_user")
public class User {
	@Id
	@Column(name = "username")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String mUsername;

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
}
