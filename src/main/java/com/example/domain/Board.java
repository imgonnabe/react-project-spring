package com.example.domain;

public class Board {// 필드값을 설정하는 생성자 또는 setter 메서드가 없어서 생성된 Board 객체는 기본값으로 초기화된다.
	private int bno;
	private String bcontent, btitle, bdate;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
}