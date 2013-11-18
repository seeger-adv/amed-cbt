package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_question")
public class Question {
	@Id
	@Column(name = "questionid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String mQuestion;

	public String getQuestion() {
		return this.mQuestion;
	}

	public void setQuestion(String question) {
		this.mQuestion = question;
	}
}
