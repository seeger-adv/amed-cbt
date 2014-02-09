package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateControllerImpl;

public class Session {
	private User mUser;
	private boolean isValid;
	private final State mState;

	public Session(User user) {
		this();
		setUser(user);
	}

	public Session() {
		this.isValid = true;
		this.mState = new State();
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

	public StateController getStateController() {
		return new StateControllerImpl(this.mUser);
	}
}
