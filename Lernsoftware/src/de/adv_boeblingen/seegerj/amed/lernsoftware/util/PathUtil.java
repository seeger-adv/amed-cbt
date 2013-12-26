package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import javax.servlet.http.HttpServletRequest;

import de.adv_boeblingen.seegerj.amed.lernsoftware.misc.Constants;

public class PathUtil {
	public static final String buildQuery(String path) {
		return String.format("http://%s:%s/%s/%s", Constants.SERVERNAME, Constants.PORT, Constants.DEPLOYMENT_PATH,
				path);
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
}
