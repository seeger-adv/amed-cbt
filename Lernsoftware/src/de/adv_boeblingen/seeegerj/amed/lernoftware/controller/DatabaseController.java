package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseController {
	private static final String CONFIGURATION = "lernsoftwareconfig";
	private static final EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory(CONFIGURATION);
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
