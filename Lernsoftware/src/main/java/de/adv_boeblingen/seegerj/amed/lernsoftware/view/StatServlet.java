package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ChapterController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.NavigationController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.StateControllerImpl;
import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.UserController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.TemplateRenderer;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.VariableMap;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Chapter;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Lesson;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Question;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Response;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.User;

@SuppressWarnings("serial")
public class StatServlet
		extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Set<StateController> userStates = new HashSet<StateController>();

		VariableMap map = VariableMap.getMappingFromRequest(req);
		if ("admin".equals(map.get(Constants.USERNAME_PARAM))) {
			for (User user : UserController.getUsers()) {
				userStates.add(new StateControllerImpl(user));
			}
		} else {
			userStates.add(NavigationController.retrieveFromSession(req));
		}

		StringBuilder out = new StringBuilder();
		out.append("<h1>Stats</h1>");
		printStats(out, userStates);

		map.put(Constants.CONTENT_PARAM, out.toString());
		new TemplateRenderer(req, "/_template.jtpl").printOutput(resp.getWriter());
	}

	private static void printStats(StringBuilder out, Set<StateController> userState) {
		out.append(Constants.Markup.TABLE_HEAD);

		printHeading(out, userState);

		for (Chapter chapter : ChapterController.getChapters()) {
			printChapterLine(out, chapter, userState, userState.size());
		}

		out.append(Constants.Markup.ROW_END).append(Constants.Markup.TABLE_END);
	}

	private static void printChapterLine(StringBuilder out, Chapter chapter, Set<StateController> state, int size) {
		out.append(Constants.Markup.ROW_HEAD);
		out.append(Constants.Markup.CELL_HEAD).append(chapter.getTitle()).append(Constants.Markup.CELL_END);
		printTimesEmpyCell(out, size + 2);
		out.append(Constants.Markup.ROW_END);

		for (Lesson lesson : chapter.getLessons()) {
			printLesson(out, lesson, state, size);
		}
	}

	private static void printLesson(StringBuilder out, Lesson lesson, Set<StateController> state, int size) {
		out.append(Constants.Markup.ROW_HEAD);
		printTimesEmpyCell(out, 1);
		out.append(Constants.Markup.CELL_HEAD).append(lesson.getTitle()).append(Constants.Markup.CELL_END);
		printTimesEmpyCell(out, size + 1);
		out.append(Constants.Markup.ROW_END);

		for (Question question : lesson.getQuestions()) {
			printQuestion(out, question, state, size);
		}
	}

	private static void printQuestion(StringBuilder out, Question question, Set<StateController> states, int size) {
		out.append(Constants.Markup.ROW_HEAD);
		printTimesEmpyCell(out, 2);
		out.append(Constants.Markup.CELL_HEAD).append(question.getUniqueLabel()).append(Constants.Markup.CELL_END);

		for (StateController state : states) {
			out.append(Constants.Markup.CELL_HEAD);
			Response response = state.getResponse(question);
			Boolean isCorrect = state.isUserResponseCorrect(response);
			if (isCorrect != null) {
				out.append(isCorrect ? Constants.Markup.CORRECT_IMG : Constants.Markup.INCORRECT_IMG);
			}
			out.append(Constants.Markup.CELL_END);
		}

		out.append(Constants.Markup.ROW_END);
	}

	private static void printHeading(StringBuilder out, Set<StateController> userState) {
		out.append(Constants.Markup.ROW_HEAD);
		printTimesEmpyCell(out, 3);

		for (StateController state : userState) {
			String username = state.getUser().getUsername();
			out.append(Constants.Markup.CELL_HEAD).append(username).append(Constants.Markup.CELL_END);
		}
		out.append(Constants.Markup.ROW_END);
	}

	private static void printTimesEmpyCell(StringBuilder out, int times) {
		for (int i = 0; i < times; i++) {
			out.append(Constants.Markup.CELL_HEAD).append(Constants.Markup.CELL_END);
		}
	}
}
