package org.sing.tcode.entity;

import java.util.Date;

public class VipInfo {
	
	private String vaccount;
	private Integer vlevel;
	private Date vstartdate;
	private Date venddate;
	private Integer vpoint;
	private String vface;
	public String getVaccount() {
		return vaccount;
	}
	public void setVaccount(String vaccount) {
		this.vaccount = vaccount;
	}
	public Integer getVlevel() {
		return vlevel;
	}
	public void setVlevel(Integer vlevel) {
		this.vlevel = vlevel;
	}
	public Date getVstartdate() {
		return vstartdate;
	}
	public void setVstartdate(Date vstartdate) {
		this.vstartdate = vstartdate;
	}
	public Date getVenddate() {
		return venddate;
	}
	public void setVenddate(Date venddate) {
		this.venddate = venddate;
	}
	public Integer getVpoint() {
		return vpoint;
	}
	public void setVpoint(Integer vpoint) {
		this.vpoint = vpoint;
	}
	public String getVface() {
		return vface;
	}
	public void setVface(String vface) {
		this.vface = vface;
	}
	
	

}
