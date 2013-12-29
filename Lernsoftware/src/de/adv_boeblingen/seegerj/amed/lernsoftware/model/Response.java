package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "t_response")
public class Response {
	@Id
	private int bla;

	@OneToOne
	@JoinColumn(name = "user")
	protected User mUser;

	@OneToOne
	@JoinColumn(name = "question")
	protected Question mQuestion;

	@OneToOne
	@JoinColumn(name = "answer")
	protected Answer mGivenAnswer;

	@Column(name = "timestamp")
	private long mTimestamp;

	public User getUser() {
		return mUser;
	}

	public void setUser(User mUser) {
		this.mUser = mUser;
	}

	public Question getQuestion() {
		return mQuestion;
	}

	public void setQuestion(Question mQuestion) {
		this.mQuestion = mQuestion;
	}

	public Answer getGivenAnswer() {
		return mGivenAnswer;
	}

	public void setGivenAnswer(Answer mGivenAnswer) {
		this.mGivenAnswer = mGivenAnswer;
	}

	public long getTimestamp() {
		return mTimestamp;
	}

	public void setTimestamp(long mTimestamp) {
		this.mTimestamp = mTimestamp;
	}
}
