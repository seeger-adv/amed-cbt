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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

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
	@OrderBy(value = "answerid")
	private final Set<Answer> mPossibleAnswers = new HashSet<Answer>();

	@Column(name = "question")
	private String mQuestion;

	public String getQuestion() {
		return this.mQuestion;
	}

	public void setQuestion(String question) {
		this.mQuestion = question;
	}
}
