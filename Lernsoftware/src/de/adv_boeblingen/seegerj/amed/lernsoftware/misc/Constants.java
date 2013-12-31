package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

public class Constants {
	public static final String BASIC_QUERY = "SELECT p FROM %s p";

	public static final String LESSON_PARAM = "lesson";
	public static final String SESSION_PARAM = "session";
	public static final String USERNAME_PARAM = "username";
	public static final String NAVIGATION_PARAM = "navigation";
	public static final String STATUS_PARAM = "status";
	public static final String CONTENT_PARAM = "content";
	public static final String BASE_URL_PARAM = "baseurl";
	public static final String ANALYTICS_KEY = "UA-46671672-1";
	public static final String SERVERNAME = "localhost";
	public static final int PORT = 8080;
	public static final String DEPLOYMENT_PATH = "Lernsoftware";

	public static final class Markup {
		public static final String FORM_START = "<form action=\"\" method=\"post\">";
		public static final String FORM_END = "</form>";
		public static final String LABEL = "<label for=\"%s\">%s</label>";
		public static final String RADIO = "<input type=\"radio\" name=\"%s\" value=\"%s\">";
		public static final String HEADLINE1 = "<h1>%s</h1>";
		public static final String BREAK = "<br>";
		public static final String PAR = "<p>%s</p>";
		public static final String SUBMIT = "<input type=\"submit\">";
	}
}
