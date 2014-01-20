package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Messages;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.CryptUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class UserController {
	/**
	 * Returns a {@link Session} on successful login, {@code null} otherwise.
	 */
	public static final Session login(String username, String password) {
		if (username == null || password == null) {
			return null;
		}

		final User foundUser = findUser(username);
		if (foundUser == null) {
			return null;
		}

		String passwordHash = CryptUtil.loginHash(password);
		if (foundUser.getPassword().equals(passwordHash)) {
			writeLoginTime(foundUser);
			return new Session(foundUser);
		} else {
			System.out.println(Messages.LOGIN_FAILED);
		}

		return null;
	}

	private static void writeLoginTime(final User foundUser) {
		final long now = new Date().getTime();

		DatabaseUtil.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				foundUser.setLastLogin(now);
				manager.merge(foundUser);
				return null;
			}
		});
	}

	private static User findUser(final String username) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<User>() {
			@Override
			public User run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(User.class, username);
			}
		});
	}

	/**
	 * Removes the Session from the DB and deletes the Session cookie.
	 */
	public static final void logout(Session session) {
		session.invalidate();
	}

	public static final Session register(final String username, final String password) {
		if (username == null || username.isEmpty()) {
			return null;
		}

		if (password == null || password.isEmpty()) {
			return null;
		}

		final long now = new Date().getTime();
		final String hash = CryptUtil.loginHash(password);

		DatabaseUtil.runTransaction(new DatabaseRunnable<Void>() {
			@Override
			public Void run(EntityManager manager, EntityTransaction transaction) {
				User user = new User();
				user.setUsername(username);
				user.setCreated(now);
				user.setLastLogin(0);
				user.setPassword(hash);
				manager.persist(user);
				return null;
			}
		});

		return login(username, password);
	}

	public static boolean isValidSession(Session session) {
		return session != null && session.isValid();
	}

	public static boolean hasPermission(User user, String action) {
		return true;
	}
}
