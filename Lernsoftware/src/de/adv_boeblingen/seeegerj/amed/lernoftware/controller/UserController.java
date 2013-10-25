package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;

public class UserController {
	/**
	 * Returns a {@link Session} on successful login, {@code null} otherwise.
	 */
	public static final Session login(String user, String password) {
		return null;
	}

	/**
	 * Removes the Session from the DB and deletes the Session cookie.
	 */
	public static final void logout(Session session) {
		
	}
	
	public static final Session register(String email, String password) {
		//register here
		return login(email, password);
	}
	
	public static boolean isValidSession(Session session) {
		return session != null;
	}
}
