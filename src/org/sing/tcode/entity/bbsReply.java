package org.sing.tcode.entity;

import java.util.Date;

/**
 * 论坛帖子回复表bbs_reply
 * @author Aidan
 *
 */
public class bbsReply {

	private Integer id;			//ID标识
	private String pid; 		//帖子的id
	private Integer cstorey;	//评论区楼层(自增长id)
	private String ucommentId;	//评论者账号
	private String ureplyId;	//回复者账号
	private String ureplyNick;	//回复者昵称
	private String content;		//回复内容
	private Date replyTime;		//回复时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getCstorey() {
		return cstorey;
	}
	public void setCstorey(Integer cstorey) {
		this.cstorey = cstorey;
	}
	public String getUcommentId() {
		return ucommentId;
	}
	public void setUcommentId(String ucommentId) {
		this.ucommentId = ucommentId;
	}
	public String getUreplyId() {
		return ureplyId;
	}
	public void setUreplyId(String ureplyId) {
		this.ureplyId = ureplyId;
	}
	public String getUreplyNick() {
		return ureplyNick;
	}
	public void setUreplyNick(String ureplyNick) {
		this.ureplyNick = ureplyNick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
}
