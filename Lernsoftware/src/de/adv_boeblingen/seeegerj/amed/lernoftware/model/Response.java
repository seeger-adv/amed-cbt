package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import javax.persistence.Entity;

@Entity
public class Response {
	private User mUser;
	private Question mQuestion;
	private Answer mGivenAnswer;

}
