package org.sing.tcode.entity;

import java.sql.Date;
/**
 *用户基础信息表 user 详细信息
 * @author Administrator
 *
 */
public class User {

	private String u_account;//用户账号
	private String u_password;//用户昵称
	private String u_email;//用户邮箱
	private String u_IP;//最后一次登录的IP
	//private Integer ifidinfo;//是否完成实名注册
	private Integer u_num;//登录次数
	private Integer u_state;//可用状态
	private String u_nick;
	
	
	
	
	
	
	
	
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public Integer getU_state() {
		return u_state;
	}
	public void setU_state(Integer u_state) {
		this.u_state = u_state;
	}
	public Integer getU_num() {
		return u_num;
	}
	public void setU_num(Integer u_num) {
		this.u_num = u_num;
	}
	public String getU_account() {
		return u_account;
	}
	public void setU_account(String u_account) {
		this.u_account = u_account;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_IP() {
		return u_IP;
	}
	public void setU_IP(String u_IP) {
		this.u_IP = u_IP;
	}
}
