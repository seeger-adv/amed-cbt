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
}
