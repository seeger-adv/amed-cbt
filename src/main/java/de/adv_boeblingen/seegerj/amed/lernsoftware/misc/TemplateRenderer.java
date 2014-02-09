package de.adv_boeblingen.seegerj.amed.lernsoftware.misc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jtpl.Template;

public class TemplateRenderer {
	private Template template;

	public TemplateRenderer(HttpServletRequest req, String filepath) throws IOException {
		HttpSession session = req.getSession();
		ServletContext ctx = session.getServletContext();
		URL resourceUrl = ctx.getResource(filepath);
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(resourceUrl.openStream());
			this.template = new Template(reader);

			VariableMap map = VariableMap.getMappingFromRequest(req);
			for (String key : map.keySet()) {
				this.template.assign(key, map.get(key));
			}

			this.template.parse("main");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public void printOutput(PrintWriter writer) {
		writer.write(this.template.out());
	}
}
