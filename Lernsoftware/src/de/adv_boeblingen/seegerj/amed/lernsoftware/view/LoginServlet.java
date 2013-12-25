package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.UserController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Session;

@WebServlet("/login")
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
		String action = req.getParameter("act");
		if (action != null) {
			if (action.equals("Registrieren")) {
				userSession = UserController.register(user, password);
			} else {
				userSession = UserController.login(user, password);
			}
		}

		req.getSession().setAttribute("session", userSession);
		resp.sendRedirect("/Lernsoftware/Lesson/");
	}
}
