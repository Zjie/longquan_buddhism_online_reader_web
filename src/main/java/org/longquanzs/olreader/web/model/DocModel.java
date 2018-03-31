package org.longquanzs.olreader.web.model;

import org.apache.lucene.document.Document;

public class DocModel {
	private int id;
	//部
	private String work;
	//经号
	private String sno;
	//经文名
	private String name;
	//卷数
	private int rollNum;
	private int rollBegin;
	private int rollEnd;
	//册号
	private String bookNo;
	//页码
	private String page;
	//朝代
	private String dynasty;
	//译者
	private String translator;
	//文件名
	private String fileName;
	//序
	private String preface;
	private String rollNo;
	private String rollName;
	private String rollText;
	
	public DocModel(int id, Document doc, boolean needFullText, boolean needFullPreface) {
		this.id = id;
		this.work = doc.get("work_name");
		this.sno = doc.get("scripture_no");
		this.name = doc.get("scripture_name").trim();
		this.bookNo = doc.get("scripture_book_no");
		this.dynasty = doc.get("scripture_dynasty");
		this.translator = doc.get("scripture_translator");
		this.rollNo = doc.get("roll_no");
		this.rollName = doc.get("roll_name").trim();
		
		this.rollText = doc.get("roll_text");
		if (!needFullText)  {
			this.rollText = this.rollText.substring(0, this.rollText.length() > 50 ? 50 : this.rollText.length());
		}
		this.preface = doc.get("scripture_preface").trim();
		if (!needFullPreface) {
			this.preface = this.preface.substring(0, this.preface.length() > 50 ? 50 : this.preface.length());
		}
		this.rollText = this.rollText.trim();
		this.preface = this.preface.trim();
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollNum() {
		return rollNum;
	}
	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}
	public int getRollBegin() {
		return rollBegin;
	}
	public void setRollBegin(int rollBegin) {
		this.rollBegin = rollBegin;
	}
	public int getRollEnd() {
		return rollEnd;
	}
	public void setRollEnd(int rollEnd) {
		this.rollEnd = rollEnd;
	}

	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getDynasty() {
		return dynasty;
	}
	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPreface() {
		return preface;
	}
	public void setPreface(String preface) {
		this.preface = preface;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getRollName() {
		return rollName;
	}
	public void setRollName(String rollName) {
		this.rollName = rollName;
	}
	public String getRollText() {
		return rollText;
	}
	public void setRollText(String rollText) {
		this.rollText = rollText;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
