package de.adv_boeblingen.seeegerj.amed.lernoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seeegerj.amed.lernoftware.TemplateRenderer;

@WebServlet("/Lesson/")
@SuppressWarnings("serial")
public class LessonServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		new TemplateRenderer(req, "/_template.jtpl").PrintOutput(resp.getWriter());
	}
}
