package com.board.dao;
/*
 * CREATE TABLE ws_board(
  b_no NUMBER PRIMARY KEY NOT NULL, --글번호(pk)
  b_Id VARCHAR2(20), -- 작성자(fk)
  b_Title VARCHAR2(50) NOT NULL, -- 제목
  b_Content CLOB NOT NULL, -- 내용
  b_Hit NUMBER, -- 조회수
  b_Renum NUMBER, -- 답변수
  b_Date DATE, --등록일
  b_saNo NUMBER REFERENCES b_Word(sa_No) -- 말머리 번호 (fk)
);
 */
import java.util.*;
public class BoardVO {
	private int b_no;
	private String b_id;
	private String b_title;
	private String b_content;
	private int b_hit;
	private int b_renum;
	private Date b_date;
	private int b_sano;
	private int num;
	private int sa_no;
	private String dbday;
	private String sa_img;
	private int replyCount;
	
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getSa_no() {
		return sa_no;
	}
	public void setSa_no(int sa_no) {
		this.sa_no = sa_no;
	}
	public String getSa_img() {
		return sa_img;
	}
	public void setSa_img(String sa_img) {
		this.sa_img = sa_img;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_hit() {
		return b_hit;
	}
	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}
	public int getB_renum() {
		return b_renum;
	}
	public void setB_renum(int b_renum) {
		this.b_renum = b_renum;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public int getB_sano() {
		return b_sano;
	}
	public void setB_sano(int b_sano) {
		this.b_sano = b_sano;
	}
	
	
}
