package de.adv_boeblingen.seeegerj.amed.lernoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.UserController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Session userSession = (Session) session.getAttribute("session");
		if (userSession != null) {
			UserController.logout(userSession);
		}
		resp.sendRedirect("/Lernsoftware");
	}
}
