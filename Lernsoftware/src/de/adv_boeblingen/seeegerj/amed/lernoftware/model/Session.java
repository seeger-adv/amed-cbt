package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

public class Session {
	private User mUser;
	private boolean isValid;
	private State mState;

	public Session() {
		this.isValid = true;
	}

	public User getUser() {
		return this.mUser;
	}

	public void setUser(User user) {
		this.mUser = user;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public void invalidate() {
		this.isValid = false;
	}

	public State getState() {
		return this.mState;
	}

	public void setmState(State state) {
		this.mState = state;
	}
}
