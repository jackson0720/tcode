package org.sing.tcode.entity;

import java.sql.Date;

public class Users {
	private String u_account;		//用户账号
	private String u_email;			//用户邮箱
	private String u_regtime;		//注册时间
	private Boolean u_state;		//是否可用

	private String utype_name;		//等级

	private String sex;				//用户性别 
	private String u_nick;			//用户昵称	 
	private String u_name;			//真实姓名
	private String u_constellation;	//星座
	private String u_tel;			//手机号码	
	private String u_idcard;		//身份证
	private String u_university;	//毕业院校
	
	public String getU_account() {
		return u_account;
	}
	public void setU_account(String u_account) {
		this.u_account = u_account;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public Boolean getU_state() {
		return u_state;
	}
	public void setU_state(Boolean u_state) {
		this.u_state = u_state;
	}
	public String getU_regtime() {
		return u_regtime;
	}
	public void setU_regtime(String u_regtime) {
		this.u_regtime = u_regtime;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_constellation() {
		return u_constellation;
	}
	public void setU_constellation(String u_constellation) {
		this.u_constellation = u_constellation;
	}
	public String getU_tel() {
		return u_tel;
	}
	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}
	 
 
	public String getU_university() {
		return u_university;
	}
	public void setU_university(String u_university) {
		this.u_university = u_university;
	}
	  
 
	public String getUtype_name() {
		return utype_name;
	}
	public void setUtype_name(String utype_name) {
		this.utype_name = utype_name;
	}
	public String getU_idcard() {
		return u_idcard;
	}
	public void setU_idcard(String u_idcard) {
		this.u_idcard = u_idcard;
	}
	public Users() {
		 
	}
	public Users(String u_account, String u_email, String u_nick, Boolean u_state, String utype_name, String u_regtime,
			String sex, String u_name, String u_constellation, String u_tel, String u_idcard, String u_university) {
		 
		this.u_account = u_account;
		this.u_email = u_email;
		this.u_nick = u_nick;
		this.u_state = u_state;
		this.utype_name = utype_name;
		this.u_regtime = u_regtime;
		this.sex = sex;
		this.u_name = u_name;
		this.u_constellation = u_constellation;
		this.u_tel = u_tel;
		this.u_idcard = u_idcard;
		this.u_university = u_university;
	}
	@Override
	public String toString() {
		return "Users [u_account=" + u_account + ", u_email=" + u_email + ", u_regtime=" + u_regtime + ", u_state="
				+ u_state + ", utype_name=" + utype_name + ", sex=" + sex + ", u_nick=" + u_nick + ", u_name=" + u_name
				+ ", u_constellation=" + u_constellation + ", u_tel=" + u_tel + ", u_idcard=" + u_idcard
				+ ", u_university=" + u_university + "]";
	}

 
	 
	
}
