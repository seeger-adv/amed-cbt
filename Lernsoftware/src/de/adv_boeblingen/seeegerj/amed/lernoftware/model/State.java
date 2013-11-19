package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

public class State {
	private User mUser;
	private Lesson mLastLesson;

	public User getUser() {
		return this.mUser;
	}

	public void setUser(User user) {
		this.mUser = user;
	}

	public Lesson getLastLesson() {
		return this.mLastLesson;
	}

	public void setLastLesson(Lesson last) {
		this.mLastLesson = last;
	}
}
