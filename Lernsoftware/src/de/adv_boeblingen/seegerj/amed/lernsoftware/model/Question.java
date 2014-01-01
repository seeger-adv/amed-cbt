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

@Entity(name = "t_question")
public class Question {
	public static final String MULTIPLE_CHOICE = "multiplechoice";

	@Id
	@Column(name = "questionid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@ManyToOne
	@JoinColumn(name = "lesson")
	private Lesson mLesson;

	@OneToOne(optional = true)
	@JoinColumn(name = "correctanswer")
	private Answer mCorrectAnswer;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionid")
	@OrderBy(value = "answerid")
	private final Set<Answer> mPossibleAnswers = new HashSet<Answer>();

	@Column(name = "question")
	private String mQuestion;

	@Column(name = "type")
	private String mType;

	public String getQuestion() {
		return this.mQuestion;
	}

	public void setQuestion(String question) {
		this.mQuestion = question;
	}

	public Lesson getLesson() {
		return mLesson;
	}

	public void setLesson(Lesson lesson) {
		this.mLesson = lesson;
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public Set<Answer> getAnswers() {
		return this.mPossibleAnswers;
	}

	public String getUniqueLabel() {
		return String.format("%s%d", "q", mId);
	}

	public String getType() {
		return mType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Question) {
			return ((Question) obj).getId() == getId();
		}
		return false;
	}
}
