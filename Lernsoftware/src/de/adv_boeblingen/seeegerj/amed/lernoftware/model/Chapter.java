package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.IndexColumn;

@Entity(name = "t_chapter")
public class Chapter {
	@Id
	@Column(name = "chapterid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@Column(name = "title")
	private String mTitle;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "chapter")
	@IndexColumn(name = "lessonid")
	private final List<Lesson> mLessons = new ArrayList<>();

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public List<Lesson> getLessons() {
		return this.mLessons;
	}

	public int getId() {
		return this.mId;
	}

	public boolean add(Lesson lesson) {
		return this.mLessons.add(lesson);
	}

	public boolean remove(Object leon) {
		return this.mLessons.remove(leon);
	}
}
