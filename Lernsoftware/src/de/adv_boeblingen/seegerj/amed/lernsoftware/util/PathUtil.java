package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {
	public static final String buildQuery(String path) {
		return String.format("http://%s:%s/%s/%s", Constants.SERVERNAME, Constants.PORT, Constants.DEPLOYMENT_PATH,
				path);
	}

	public static int retrieveLessonId(HttpServletRequest req) {
		String pathInfo = req.getPathInfo();
		String[] lessonSegment = pathInfo.split("/");

		if (lessonSegment.length < 2) {
			return -1;
		}

		String lesson = lessonSegment[1];
		if (lesson == null) {
			return -1;
		}

		return Integer.parseInt(lesson);
	}
}
