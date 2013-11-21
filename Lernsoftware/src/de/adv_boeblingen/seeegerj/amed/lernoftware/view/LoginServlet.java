package de.adv_boeblingen.seeegerj.amed.lernoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seeegerj.amed.lernoftware.controller.UserController;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Session;

@WebServlet("/login")
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		new TemplateRenderer(req, "/login.jtpl").PrintOutput(resp.getWriter());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getParameter("loginusername");
		String password = req.getParameter("loginpass");
		Session userSession = UserController.login(user, password);
		req.getSession().setAttribute("session", userSession);
		resp.sendRedirect("/Lernsoftware/Lesson/");
	}
}
