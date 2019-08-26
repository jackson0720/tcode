package org.sing.tcode.entity;

import java.sql.Date;

/**
 * 板块表plate详细信息
 * @author Aidan
 *
 */
public class Plate {

	private String p_no;		//板块编号
	private String p_name;		//板块名称
	private Date p_time;		//板块建立时间
	private String p_account;	//板块建立时间
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Date getP_time() {
		return p_time;
	}
	public void setP_time(Date p_time) {
		this.p_time = p_time;
	}
	public String getP_account() {
		return p_account;
	}
	public void setP_account(String p_account) {
		this.p_account = p_account;
	}
	
	
}
