package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.User;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.CryptUtil;

public class UserController {
	/**
	 * Returns a {@link Session} on successful login, {@code null} otherwise.
	 */
	public static final Session login(String username, String password) {
		if(username == null || password == null) {
			return null;
		}

		User foundUser = findUser(username);
		if(foundUser == null)  {
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
		if(username.equals("admin")) {
			User user = new User();
			user.setUsername("admin");
			String pass = CryptUtil.toSHA1("admin");
			user.setPassword(pass);
			return user;
		}

		return null;
	}
	
	/**
	 * Removes the Session from the DB and deletes the Session cookie.
	 */
	public static final void logout(Session session) {
		session.invalidate();
	}
	
	public static final Session register(String email, String password) {
		//register here
		return login(email, password);
	}
	
	public static boolean isValidSession(Session session) {
		return session != null && session.isValid();
	}

	public static boolean hasPermission(User user, String action) {
		return true;
	}
}
