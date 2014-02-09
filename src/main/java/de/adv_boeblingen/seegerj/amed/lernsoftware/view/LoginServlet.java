package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.UserController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.TemplateRenderer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;

@SuppressWarnings("serial")
public class LoginServlet
extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new TemplateRenderer(req, "/login.jtpl").printOutput(resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("loginusername");
		String password = req.getParameter("loginpass");

		Session userSession = null;
		HttpSession session = req.getSession();
		String action = req.getParameter("act");
		if (action != null) {
			if (action.equals("Registrieren")) {
				userSession = UserController.register(user, password);

				if (userSession == null) {
					setMessage(session, "Registration failed!");
				}
			} else {
				userSession = UserController.login(user, password);

				if (userSession == null) {
					setMessage(session, "Login failed!");
				}
			}

			if (userSession != null) {
				setMessage(session, "");
			}
		}

		session.setAttribute("session", userSession);
		resp.sendRedirect("/Lernsoftware/Lesson/");
	}

	private void setMessage(HttpSession session, String message) {
		session.setAttribute(Constants.MESSAGE_PARAM, message);
	}
}
