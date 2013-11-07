package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

public class Session {
	private User mUser;
	private boolean isValid;

	public Session() {
		isValid = true;
	}
	
	public User getUser() {
		return mUser;
	}

	public void setUser(User user) {
		this.mUser = user;
	}

	public boolean isValid() {
		return isValid;
	}

	public void invalidate() {
		isValid = false;
	}
}
