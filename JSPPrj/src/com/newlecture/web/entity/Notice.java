package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	int id;
	String title;
	Date regdate;
	String regdate_S;
	String writer_id;
	int hit;
	String files;
	String content;
	int pub;
	
	public Notice() {
	}
	public Notice(int id, String title, Date regdate, String writer_id, int hit, String files, String content) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.writer_id = writer_id;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}
	
	
	public Notice(int id, String title, String regdate_S, String writer_id, int hit, String files, String content,
			int pub) {
		super();
		this.id = id;
		this.title = title;
		this.regdate_S = regdate_S;
		this.writer_id = writer_id;
		this.hit = hit;
		this.files = files;
		this.content = content;
		this.pub = pub;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate_S() {
		return regdate_S;
	}
	public void setRegdate_S(String regdate_S) {
		this.regdate_S = regdate_S;
	}
	public int getPub() {
		return pub;
	}
	public void setPub(int pub) {
		this.pub = pub;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", regdate=" + regdate + ", writer_id=" + writer_id + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
}
