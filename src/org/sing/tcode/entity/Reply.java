package org.sing.tcode.entity;

/**
 * 论坛帖子回复表bbs_reply与posts表的结合
 * @author Aidan
 *
 */
public class Reply {

	private Integer id;			//ID标识
	private String pid; 		//帖子的id
	private Integer cstorey;	//评论区楼层(自增长id)
	private String ucommentId;	//评论者账号
	private String ureplyId;	//回复者账号
	private String ureplyNick;	//回复者昵称
	private String content;		//回复内容
	private String replyTime;		//回复时间
	private Integer no;			//评论楼层
	private String cid;			//帖子id
	private String text;		//评论内容
	private String accouont;	//评论者账号
	private String ctime;		//评论事件
	
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
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAccouont() {
		return accouont;
	}
	public void setAccouont(String accouont) {
		this.accouont = accouont;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
	
}
