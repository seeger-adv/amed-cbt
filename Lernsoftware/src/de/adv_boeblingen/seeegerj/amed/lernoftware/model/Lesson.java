package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.List;
import java.util.UUID;

public class Lesson {
	private UUID mId;
	private String mTitle;
	List<Question> mQuestions;

	public Lesson() {
		mId = UUID.randomUUID();
	}
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String title) {
		this.mTitle = title;
	}
	public List<Question> getQuestions() {
		return mQuestions;
	}

	public UUID getId() {
		return mId;
	}
	
	public boolean add(Question question) {
		return mQuestions.add(question);
	}
	public boolean remove(Object question) {
		return mQuestions.remove(question);
	}
}
