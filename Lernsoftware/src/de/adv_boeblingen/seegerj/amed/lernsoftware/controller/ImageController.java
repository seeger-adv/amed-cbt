package de.adv_boeblingen.seegerj.amed.lernsoftware.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.adv_boeblingen.seegerj.amed.lernsoftware.model.Image;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil;
import de.adv_boeblingen.seegerj.amed.lernsoftware.util.DatabaseUtil.DatabaseRunnable;

public class ImageController {
	public static Image getImage(final int id) {
		return DatabaseUtil.runQuery(new DatabaseRunnable<Image>() {
			@Override
			public Image run(EntityManager manager, EntityTransaction transaction) {
				return manager.find(Image.class, id);
			}
		});
	}
}
