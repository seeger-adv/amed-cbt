package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.adv_boeblingen.seegerj.amed.lernsoftware.util.CryptUtil;

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

	public String getAnswer() {
		return this.mAnswer;
	}

	public int getId() {
		return this.mId;
	}

	public Question getQuestion() {
		return this.mQuestion;
	}

	public String getUniqueLabel() {
		return String.format("%s%s%d", this.mQuestion.getUniqueLabel(), "a", this.mId);
	}

	public String getHiddenLabel() {
		return CryptUtil.md5(getUniqueLabel());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Answer) {
			return ((Answer) obj).getId() == getId();
		}
		return false;
	}
}
