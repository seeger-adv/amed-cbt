package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.ArrayList;
import java.util.List;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Chapter;
import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;

public class ChapterController {
	public static List<Chapter> getChapters() {
		List<Chapter> chapters = new ArrayList<>();
		setupTestData(chapters);
		return chapters;
	}

	private static void setupTestData(List<Chapter> chapters) {
		// bla from db
		Chapter osi = new Chapter();
		osi.setTitle("OSI Stack");
		chapters.add(osi);

		Lesson l1 = new Lesson();
		l1.setTitle("Layer 1");
		osi.add(l1);

		Lesson l2 = new Lesson();
		l2.setTitle("Layer 2");
		osi.add(l2);

		Lesson l3 = new Lesson();
		l3.setTitle("Layer 3");
		osi.add(l3);
	}

	public static boolean isChapterComplete(Chapter chapter) {
		// TODO Auto-generated method stub
		return false;
	}
}
