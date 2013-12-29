package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;
import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.UriBuilder;

public class PathUtil {
	public static final UriBuilder getBaseUriBuilder() {
		UriBuilder builder = new UriBuilder();
		builder.setScheme("http");
		builder.setHost(Constants.SERVERNAME);
		builder.setPort(Constants.PORT);
		builder.setPath(Constants.DEPLOYMENT_PATH);
		return builder;
	}

	public static int retrieveLessonId(HttpServletRequest req) {
		String[] lessonSegment = spliteRequestElements(req);

		if (lessonSegment.length < 2) {
			return -1;
		}

		String lesson = lessonSegment[1];
		if (lesson == null) {
			return -1;
		}

		return Integer.parseInt(lesson);
	}

	private static String[] spliteRequestElements(HttpServletRequest req) {
		String pathInfo = req.getPathInfo();
		String[] lessonSegment = pathInfo.split("/");
		return lessonSegment;
	}

	public static String retrieveAction(HttpServletRequest req) {
		String[] actionSegment = spliteRequestElements(req);

		if (actionSegment.length > 0) {
			return actionSegment[0];
		}

		return null;
	}

	public static int getCurrentQuestion(HttpServletRequest req) {
		String[] lessonSegment = spliteRequestElements(req);

		if (lessonSegment.length < 4) {
			return 0;
		}

		String lesson = lessonSegment[3];
		if (lesson == null) {
			return 0;
		}

		return Integer.parseInt(lesson);
	}
}
