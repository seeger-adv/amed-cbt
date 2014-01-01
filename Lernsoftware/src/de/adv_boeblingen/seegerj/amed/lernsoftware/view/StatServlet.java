package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.TemplateRenderer;

@WebServlet("/Stats/*")
@SuppressWarnings("serial")
public class StatServlet
		extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new TemplateRenderer(req, "/stats.jtpl").printOutput(resp.getWriter());
	}
}
