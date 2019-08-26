package org.sing.tcode.entity;

import java.util.Date;

/**
 * 用户扩展信息表userinfo详细信息
 * @author Aidan
 *
 */
public class UserInfo {

	private String u_account;		//用户账号
	private String u_email;			//用户邮箱
	private String u_nick;			//用户昵称
	private Integer u_points;		//用户积分
	private Integer ifidinfo;		//是否完成实名认证
	private Integer u_groupid;      //所在用户组ID
	private String u_group;			//所在用户组
	private Integer u_state;		//是否可用
	private Date u_regtime;			//注册时间
	private Date u_birthday;		//用户生日
	private String sex;				//用户性别
	private String u_idinfo;		//实名认证码
	private String u_name;			//真实姓名
	private String u_constellation;	//星座
	private String u_tel;			//手机号码
	private String u_idcard;		//证件号码
	private String u_career;		//职业
	private String u_dev;			//擅长领域
	private String u_university;	//毕业院校
	private String u_qq;			//QQ账号
	private String u_wechat;		//微信号
	private String u_weibo;			//微博号
	private String u_remark;		//个人简介
	private String u_pic;			//头像
	
	public Integer getU_groupid() {
		return u_groupid;
	}
	public void setU_groupid(Integer u_groupid) {
		this.u_groupid = u_groupid;
	}
	public String getU_pic() {
		return u_pic;
	}
	public void setU_pic(String u_pic) {
		this.u_pic = u_pic;
	}
	public Integer getIfidinfo() {
		return ifidinfo;
	}
	public void setIfidinfo(Integer ifidinfo) {
		this.ifidinfo = ifidinfo;
	}
	public String getU_group() {
		return u_group;
	}
	public void setU_group(String u_group) {
		this.u_group = u_group;
	}
	public Integer getU_state() {
		return u_state;
	}
	public void setU_state(Integer u_state) {
		this.u_state = u_state;
	}
	public Integer getU_points() {
		return u_points;
	}
	public void setU_points(Integer u_points) {
		this.u_points = u_points;
	}
	public Date getU_regtime() {
		return u_regtime;
	}
	public void setU_regtime(Date u_regtime) {
		this.u_regtime = u_regtime;
	}
	public String getU_remark() {
		return u_remark;
	}
	public void setU_remark(String u_remark) {
		this.u_remark = u_remark;
	}
	public String getU_account() {
		return u_account;
	}
	public void setU_account(String u_account) {
		this.u_account = u_account;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public Date getU_birthday() {
		return u_birthday;
	}
	public void setU_birthday(Date u_birthday) {
		this.u_birthday = u_birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getU_idinfo() {
		return u_idinfo;
	}
	public void setU_idinfo(String u_idinfo) {
		this.u_idinfo = u_idinfo;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
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
	public String getU_idcard() {
		return u_idcard;
	}
	public void setU_idcard(String u_idcard) {
		this.u_idcard = u_idcard;
	}
	public String getU_career() {
		return u_career;
	}
	public void setU_career(String u_career) {
		this.u_career = u_career;
	}
	public String getU_dev() {
		return u_dev;
	}
	public void setU_dev(String u_dev) {
		this.u_dev = u_dev;
	}
	public String getU_university() {
		return u_university;
	}
	public void setU_university(String u_university) {
		this.u_university = u_university;
	}
	public String getU_qq() {
		return u_qq;
	}
	public void setU_qq(String u_qq) {
		this.u_qq = u_qq;
	}
	public String getU_wechat() {
		return u_wechat;
	}
	public void setU_wechat(String u_wechat) {
		this.u_wechat = u_wechat;
	}
	public String getU_weibo() {
		return u_weibo;
	}
	public void setU_weibo(String u_weibo) {
		this.u_weibo = u_weibo;
	}
	@Override
	public String toString() {
		return "UserInfo [u_account=" + u_account + ", u_email=" + u_email + ", u_nick=" + u_nick + ", u_points="
				+ u_points + ", ifidinfo=" + ifidinfo + ", u_groupid=" + u_groupid + ", u_group=" + u_group
				+ ", u_state=" + u_state + ", u_regtime=" + u_regtime + ", u_birthday=" + u_birthday + ", sex=" + sex
				+ ", u_idinfo=" + u_idinfo + ", u_name=" + u_name + ", u_constellation=" + u_constellation + ", u_tel="
				+ u_tel + ", u_idcard=" + u_idcard + ", u_career=" + u_career + ", u_dev=" + u_dev + ", u_university="
				+ u_university + ", u_qq=" + u_qq + ", u_wechat=" + u_wechat + ", u_weibo=" + u_weibo + ", u_remark="
				+ u_remark + ", u_pic=" + u_pic + "]";
	}
	
	
	
	
}
