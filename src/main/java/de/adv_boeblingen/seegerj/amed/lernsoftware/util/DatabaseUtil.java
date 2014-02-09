package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.EntityManagerFactoryHelper;

public class DatabaseUtil {
	public static <T> void runTransaction(DatabaseRunnable<T> runnable) {
		EntityManager manager = EntityManagerFactoryHelper.createEntityManager();
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
		EntityManager manager = EntityManagerFactoryHelper.createEntityManager();
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
