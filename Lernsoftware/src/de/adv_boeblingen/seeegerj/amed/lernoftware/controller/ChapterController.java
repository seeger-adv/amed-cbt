package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;

public class ChapterController {
	public static List<Chapter> getChapters() {
		EntityManager manager = DatabaseController.getEntityManager();
		try {
			String queryString = String.format("SELECT p FROM %s p",
					Chapter.class.getName());
			Query query = manager.createQuery(queryString);

			@SuppressWarnings("unchecked")
			List<Chapter> all = query.getResultList();
			if (all == null) {
				return new ArrayList<>();
			}
			return all;
		} finally {
			manager.close();
		}
	}

	public static boolean isChapterComplete(Chapter chapter) {
		// TODO Auto-generated method stub
		return false;
	}
}
