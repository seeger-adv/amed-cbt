package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OrderBy;

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
	@OrderBy(value = "questionid")
	Set<Question> mQuestions = new HashSet<Question>();

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public Set<Question> getQuestions() {
		return this.mQuestions;
	}

	public int getId() {
		return this.mId;
	}

	public void addQuestion(Question question) {
		this.mQuestions.add(question);
	}

	public void removeQuestion(Question question) {
		this.mQuestions.remove(question);
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
