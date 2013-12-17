package de.adv_boeblingen.seeegerj.amed.lernoftware.util;

import javax.servlet.http.HttpServletRequest;

public class PathUtil {
	public static final String buildQuery(String path) {
		return "/Lernsoftware/login";
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
