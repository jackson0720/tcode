package org.sing.tcode.entity;

import java.util.Date;
/**
 * 评论表comment详细信息
 * @author Aidan
 *
 */
public class Comment {

	private Integer c_no;		//评论编号
	private String c_cid;		//帖子ID
	private String c_text;		//帖子内容
	private String c_uaccount;	//发帖人
	private String c_caccount;	//回帖人
	private Date c_ctime;		//回帖时间
	public Integer getC_no() {
		return c_no;
	}
	public void setC_no(Integer c_no) {
		this.c_no = c_no;
	}
	public String getC_cid() {
		return c_cid;
	}
	public void setC_cid(String c_cid) {
		this.c_cid = c_cid;
	}
	public String getC_text() {
		return c_text;
	}
	public void setC_text(String c_text) {
		this.c_text = c_text;
	}
	public String getC_uaccount() {
		return c_uaccount;
	}
	public void setC_uaccount(String c_uaccount) {
		this.c_uaccount = c_uaccount;
	}
	public String getC_caccount() {
		return c_caccount;
	}
	public void setC_caccount(String c_caccount) {
		this.c_caccount = c_caccount;
	}
	public Date getC_ctime() {
		return c_ctime;
	}
	public void setC_ctime(Date c_ctime) {
		this.c_ctime = c_ctime;
	}
	
	
}
