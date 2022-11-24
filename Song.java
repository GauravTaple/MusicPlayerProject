package com.jspiders.musicplayerproject1.object;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class Song {
	private int id;
	private String name;
	private String movie;
	private float length;
	private String singer;
	private String composer;
	private String lyricist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getLyricist() {
		return lyricist;
	}

	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", movie=" + movie + ", length=" + length + ", singer=" + singer
				+ ", composer=" + composer + ", lyricist=" + lyricist + "]";
	}

}
