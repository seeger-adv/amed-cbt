package de.adv_boeblingen.seeegerj.amed.lernoftware;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;

import net.sf.jtpl.Template;


public class TemplateRenderer {
	private Template template;

	public TemplateRenderer(ServletContext ctx, String filepath) throws IOException {
		this(ctx, filepath, null);
	}
	
	public TemplateRenderer(ServletContext ctx, String filepath, VariableCallback callback) throws IOException {
		URL resourceUrl = ctx.getResource(filepath);
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(resourceUrl.openStream());
			template = new Template(reader);
			
			if(callback != null) {
				callback.setVariables(template);
			}
			template.parse("main");
		} finally {
			if(reader!=null) {
				reader.close();
			}
		}
	}
	
	public void PrintOutput(PrintWriter writer) {
		writer.write(template.out());
	}
}
