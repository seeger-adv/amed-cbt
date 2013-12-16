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
import javax.persistence.OneToOne;

import org.hibernate.annotations.IndexColumn;

@Entity(name = "t_question")
public class Question {
	@Id
	@Column(name = "questionid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@ManyToOne
	@JoinColumn(name = "lesson", insertable = false, updatable = false, nullable = false)
	private Lesson mLesson;

	@OneToOne
	private Answer mCorrectAnswer;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "answer")
	@IndexColumn(name = "answerid")
	List<Question> mPossibleAnswers = new ArrayList<>();

	@Column(name = "question")
	private String mQuestion;

	public String getQuestion() {
		return this.mQuestion;
	}

	public void setQuestion(String question) {
		this.mQuestion = question;
	}
}
