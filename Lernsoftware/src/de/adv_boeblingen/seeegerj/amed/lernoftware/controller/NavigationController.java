package de.adv_boeblingen.seeegerj.amed.lernoftware.controller;

import java.util.ArrayList;
import java.util.List;

import de.adv_boeblingen.seeegerj.amed.lernoftware.model.Lesson;

public class NavigationController {
	public static List<Lesson> getNavigation() {
		List<Lesson> lessons = new ArrayList<>();
		//bla from db
		Lesson osi = new Lesson();
		osi.setTitle("OSI Stack");
		lessons.add(osi);
		return lessons;
	}
}
