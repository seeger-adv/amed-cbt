package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.List;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.util.GetAllQuery;

public class ChapterController {
	public static List<Chapter> getChapters() {
		return DatabaseController.runQuery(new GetAllQuery<>(Chapter.class));
	}



	public static boolean isChapterComplete(Chapter chapter) {
		// TODO Auto-generated method stub
		return false;
	}
}
