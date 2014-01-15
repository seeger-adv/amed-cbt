package de.adv_boeblingen.seegerj.amed.lernsoftware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_iamge")
public class Image {
	@Id
	@Column(name = "chapterid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;

	@Column(name = "title")
	private byte[] mContent;

	@Column(name = "type")
	private String mType;

	public int getId() {
		return this.mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public byte[] getContent() {
		return this.mContent;
	}

	public void setContent(byte[] content) {
		this.mContent = content;
	}

	public String getType() {
		return this.mType;
	}

	public void setType(String type) {
		this.mType = type;
	}
}
