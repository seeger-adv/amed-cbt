package de.adv_boeblingen.seeegerj.amed.lernoftware;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.UserController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;

@WebServlet("/Lesson")
@SuppressWarnings("serial")
public class Router extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Session session = (Session) req.getSession().getAttribute("session");
		if(!UserController.isValidSession(session)) {
			getServletContext().getRequestDispatcher("/login.html").forward(req, resp);
		}
	}
}
