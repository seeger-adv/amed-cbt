package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DatabaseController {
	private static final String CONFIGURATION = "lernsoftwareconfig";

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(CONFIGURATION)
				.createEntityManager();
	}
}
