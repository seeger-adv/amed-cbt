package de.adv_boeblingen.seegerj.amed.lernsoftware.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.ImageController;
import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Image;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.PathUtil;

@SuppressWarnings("serial")
public class ImageServlet
		extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int id = PathUtil.getFirstUrlSegmentAsId(req);

		Image image = ImageController.getImage(id);
		if (image != null) {
			resp.setContentType(image.getType());

			PrintWriter writer = resp.getWriter();
			writer.append(image.getContent().toString());
			writer.flush();
			writer.close();
		}
	}
}
