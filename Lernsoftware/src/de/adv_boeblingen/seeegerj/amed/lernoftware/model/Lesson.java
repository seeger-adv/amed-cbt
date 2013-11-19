package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "t_lesson")
public class Lesson {
	@Id
	@Column(name = "lessonid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID mId;

	@Column(name = "title")
	private String mTitle;

	@OneToMany
	@JoinColumn(name = "questionid")
	List<Question> mQuestions;

	public Lesson() {
		this.mId = UUID.randomUUID();
		this.mQuestions = new ArrayList<>();
	}

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public List<Question> getQuestions() {
		return this.mQuestions;
	}

	public UUID getId() {
		return this.mId;
	}

	public boolean add(Question question) {
		return this.mQuestions.add(question);
	}

	public boolean remove(Object question) {
		return this.mQuestions.remove(question);
	}
}
