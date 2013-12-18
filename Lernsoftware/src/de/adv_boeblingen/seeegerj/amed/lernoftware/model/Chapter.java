package de.adv_boeblingen.seeegerj.amed.lernoftware.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
	@OrderBy(value = "lessonid")
	private final Set<Lesson> mLessons = new HashSet<>();

	public String getTitle() {
		return this.mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public Set<Lesson> getLessons() {
		return this.mLessons;
	}

	public int getId() {
		return this.mId;
	}

	public void add(Lesson lesson) {
		this.mLessons.add(lesson);
	}

	public void remove(Object leon) {
		this.mLessons.remove(leon);
	}
}
