package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.util.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.VariableMap;

@WebServlet("/Quiz/*")
@SuppressWarnings("serial")
public class QuizServlet extends HttpServlet {

	public QuizServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();

		VariableMap map = VariableMap.getMappingFromRequest(req);
		map.put(Constants.CONTENT_PARAM, renderQuiz(req));

		new TemplateRenderer(req, "/_template.jtpl").PrintOutput(writer);
	}

	private String renderQuiz(HttpServletRequest req) {
		return "";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}
}
