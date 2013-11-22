package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.adv_boeblingen.seeegerj.amed.lernoftware.util.Configuration;

public class DatabaseController {
	private static final EntityManagerFactory factory;

	static {
		factory = Persistence
				.createEntityManagerFactory(Configuration.DATABASE_CONFIG);
	}

	public static <T> T runTransaction(TransactionRunnable<T> runnable) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			return runnable.run(manager, transaction);
		} finally {
			transaction.commit();
			manager.close();
		}
	}

	public static interface TransactionRunnable<T> {
		public T run(EntityManager manager, EntityTransaction transaction);
	}
}
