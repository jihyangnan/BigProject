package com.board.dao;
/*
CREATE TABLE ws_Word(
  sa_No NUMBER PRIMARY KEY NOT NULL,--말머리 번호 (pk)
  sa_Name VARCHAR2(20), -- 말머리 이름
  sa_img VARCHAR2(50)
);
 */
import java.util.*;
public class ws_WordVO {
	private int sa_no;
	private String sa_name;
	private String sa_img;
	public int getSa_no() {
		return sa_no;
	}
	public void setSa_no(int sa_no) {
		this.sa_no = sa_no;
	}
	public String getSa_name() {
		return sa_name;
	}
	public void setSa_name(String sa_name) {
		this.sa_name = sa_name;
	}
	public String getSa_img() {
		return sa_img;
	}
	public void setSa_img(String sa_img) {
		this.sa_img = sa_img;
	}
	
	
}
