package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "t_chapter")
public class Chapter {
	@Id
	@Column(name = "chapterid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final UUID mId;

	@Column(name = "title")
	private String mTitle;

	@OneToMany
	@JoinColumn(name = "lessonid")
	List<Lesson> mLessons;

	public Chapter() {
		this.mId = UUID.randomUUID();
		this.mLessons = new ArrayList<>();
	}

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public List<Lesson> getLessons() {
		return this.mLessons;
	}

	public UUID getId() {
		return this.mId;
	}

	public boolean add(Lesson lesson) {
		return this.mLessons.add(lesson);
	}

	public boolean remove(Object leon) {
		return this.mLessons.remove(leon);
	}
}
