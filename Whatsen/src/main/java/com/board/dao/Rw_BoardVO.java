package com.board.dao;
/*
CREATE TABLE Rw_board(
  rw_No NUMBER PRIMARY KEY NOT NULL, --답글번호 (pk)
  rw_Order NUMBER , --순서번호
  rw_Content CLOB NOT NULL, --내용
  rw_Id VARCHAR2(20), -- 작성자 (fk)
  rw_Date DATE NOT NULL, --등록일
  rw_bNo NUMBER REFERENCES p_Board(b_No), --그룹번호(fk)
  group_id NUMBER,
  group_tab NUMBER,
  group_step NUMBER,
  root NUMBER,
  depth NUMBER
);
 */
import java.util.*;
public class Rw_BoardVO {
	private int rw_no;
	private int rw_order;
	private String rw_content;
	private String rw_id;
	private Date rw_date;
	private int rw_bno;
	private int group_id;
	private int group_tab;
	private int group_step;
	private int root;
	private int depth;
	private String dbday;
	
	
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public int getRw_no() {
		return rw_no;
	}
	public void setRw_no(int rw_no) {
		this.rw_no = rw_no;
	}
	public int getRw_order() {
		return rw_order;
	}
	public void setRw_order(int rw_order) {
		this.rw_order = rw_order;
	}
	public String getRw_content() {
		return rw_content;
	}
	public void setRw_content(String rw_content) {
		this.rw_content = rw_content;
	}
	public String getRw_id() {
		return rw_id;
	}
	public void setRw_id(String rw_id) {
		this.rw_id = rw_id;
	}
	public Date getRw_date() {
		return rw_date;
	}
	public void setRw_date(Date rw_date) {
		this.rw_date = rw_date;
	}
	public int getRw_bno() {
		return rw_bno;
	}
	public void setRw_bno(int rw_bno) {
		this.rw_bno = rw_bno;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
}
