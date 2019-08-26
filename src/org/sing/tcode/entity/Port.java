package org.sing.tcode.entity;

public class Port {
	private String pno;
	private String paccount;
	private String ptype;
	private String ptext;
	private String ptime;
	private Integer ifsuccess;
	private String Spplate;
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public String getPaccount() {
		return paccount;
	}
	public void setPaccount(String paccount) {
		this.paccount = paccount;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getPtext() {
		return ptext;
	}
	public void setPtext(String ptext) {
		this.ptext = ptext;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public Integer getIfsuccess() {
		return ifsuccess;
	}
	public void setIfsuccess(Integer ifsuccess) {
		this.ifsuccess = ifsuccess;
	}
	public String getSpplate() {
		return Spplate;
	}
	public void setSpplate(String spplate) {
		Spplate = spplate;
	}
	public Port(String pno, String paccount, String ptype, String ptext, String ptime, Integer ifsuccess,
			String spplate) {
		 
		this.pno = pno;
		this.paccount = paccount;
		this.ptype = ptype;
		this.ptext = ptext;
		this.ptime = ptime;
		this.ifsuccess = ifsuccess;
		Spplate = spplate;
	}
	public Port() {
		 
	}
	@Override
	public String toString() {
		return "Port [pno=" + pno + ", paccount=" + paccount + ", ptype=" + ptype + ", ptext=" + ptext + ", ptime="
				+ ptime + ", ifsuccess=" + ifsuccess + ", Spplate=" + Spplate + "]";
	}
	
	
}
