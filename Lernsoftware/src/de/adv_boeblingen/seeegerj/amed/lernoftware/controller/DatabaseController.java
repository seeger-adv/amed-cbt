package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seeegerj.amed.lernoftware.util.EMF;

public class DatabaseController {
	public static <T> void runTransaction(DatabaseRunnable<T> runnable) {
		EntityManager manager = EMF.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			runnable.run(manager, transaction);
		} finally {
			transaction.commit();
			manager.close();
		}
	}

	public static <T> T runQuery(DatabaseRunnable<T> runnable) {
		EntityManager manager = EMF.createEntityManager();
		try {
			return runnable.run(manager, null);
		} finally {
			manager.close();
		}
	}

	public static interface DatabaseRunnable<T> {
		public T run(EntityManager manager, EntityTransaction transaction);
	}
}
