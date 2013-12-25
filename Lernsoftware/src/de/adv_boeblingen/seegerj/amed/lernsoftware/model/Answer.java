package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "t_answer")
public class Answer {
	@Id
	@Column(name = "answerid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@OneToOne
	@JoinColumn(name = "questionid")
	private Question mQuestion;

	@Column(name = "answer")
	private String mAnswer;
}
