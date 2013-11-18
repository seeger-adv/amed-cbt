package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.User;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.CryptUtil;

public class UserController {
	/**
	 * Returns a {@link Session} on successful login, {@code null} otherwise.
	 */
	public static final Session login(String username, String password) {
		if (username == null || password == null) {
			return null;
		}

		User foundUser = findUser(username);
		if (foundUser == null) {
			return null;
		}

		String passwordHash = CryptUtil.toSHA1(password);
		if (foundUser.getPassword().equals(passwordHash)) {
			Session session = new Session();
			session.setUser(foundUser);
			return session;
		} else {
			System.out.println("Failed login attempt!");
		}

		return null;
	}

	private static User findUser(String username) {
		EntityManager em = DatabaseController.getEntityManager();
		return em.find(User.class, username);
	}

	/**
	 * Removes the Session from the DB and deletes the Session cookie.
	 */
	public static final void logout(Session session) {
		session.invalidate();
	}

	public static final Session register(String username, String password) {
		EntityManager em = DatabaseController.getEntityManager();

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		User user = new User();
		String hash = CryptUtil.toSHA1(password);
		user.setUsername(username);
		user.setPassword(hash);
		em.persist(user);
		transaction.commit();

		return login(username, password);
	}

	public static boolean isValidSession(Session session) {
		return session != null && session.isValid();
	}

	public static boolean hasPermission(User user, String action) {
		return true;
	}
}
