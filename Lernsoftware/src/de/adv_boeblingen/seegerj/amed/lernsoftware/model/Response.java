package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name = "t_response")
public class Response implements Serializable {
	@Id
	private int bla;

	@OneToOne
	protected User mUser;

	@OneToOne
	protected Question mQuestion;

	@OneToOne
	protected Answer mGivenAnswer;

	@Column(name = "timestamp")
	private long mTimestamp;
}
