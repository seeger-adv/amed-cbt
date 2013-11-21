package de.adv_boeblingen.seeegerj.amed.lernoftware.view;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import net.sf.jtpl.Template;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.VariableMap;

public class TemplateRenderer {
	private Template template;

	public TemplateRenderer(ServletRequest req, String filepath)
			throws IOException {
		ServletContext ctx = req.getServletContext();
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

	public void PrintOutput(PrintWriter writer) {
		writer.write(this.template.out());
	}
}
