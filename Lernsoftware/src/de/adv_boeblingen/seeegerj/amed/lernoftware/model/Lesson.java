package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

@Entity(name = "t_lesson")
public class Lesson {
	@Id
	@Column(name = "lessonid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@Column(name = "title")
	private String mTitle;

	@Column(name = "content")
	private String mContent;

	@ManyToOne
	@JoinColumn(name = "chapter", insertable = false, updatable = false, nullable = false)
	private Chapter mChapter;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "lesson")
	@IndexColumn(name = "questionid")
	List<Question> mQuestions = new ArrayList<>();

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public List<Question> getQuestions() {
		return this.mQuestions;
	}

	public int getId() {
		return this.mId;
	}

	public boolean addQuestion(Question question) {
		return this.mQuestions.add(question);
	}

	public boolean removeQuestion(Question question) {
		return this.mQuestions.remove(question);
	}

	public Chapter getChapter() {
		return this.mChapter;
	}

	public String getContent() {
		return this.mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}
}
