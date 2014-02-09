package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.UserController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;

/**
 * Servlet implementation class LogoutServlet
 */
@SuppressWarnings("serial")
public class LogoutServlet
extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Session userSession = (Session) session.getAttribute("session");
		if (userSession != null) {
			UserController.logout(userSession);
		}
		resp.sendRedirect("/Lernsoftware");
	}
}
