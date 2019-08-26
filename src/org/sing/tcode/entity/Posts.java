package org.sing.tcode.entity;

import java.util.Date;

/**
 * 帖子表posts详细信息
 * @author Aidan
 *
 */
public class Posts {

	private String p_pid;		//帖子ID
	private String p_ptheme;	//帖子标题
	private Date p_ptime;		//帖子发表时间
	private String p_paccount;	//发帖人
	private String p_pnick;		//发帖人昵称
	private String p_ptext;		//帖子内容
	private Integer p_pgood;	//帖子点赞数
	private String p_pplate;	//所属板块
	
	
	public String getP_pnick() {
		return p_pnick;
	}
	public void setP_pnick(String p_pnick) {
		this.p_pnick = p_pnick;
	}
	public String getP_pid() {
		return p_pid;
	}
	public void setP_pid(String p_pid) {
		this.p_pid = p_pid;
	}
	public String getP_ptheme() {
		return p_ptheme;
	}
	public void setP_ptheme(String p_ptheme) {
		this.p_ptheme = p_ptheme;
	}
	public Date getP_ptime() {
		return p_ptime;
	}
	public void setP_ptime(Date p_ptime) {
		this.p_ptime = p_ptime;
	}
	public String getP_paccount() {
		return p_paccount;
	}
	public void setP_paccount(String p_paccount) {
		this.p_paccount = p_paccount;
	}
	public String getP_ptext() {
		return p_ptext;
	}
	public void setP_ptext(String p_ptext) {
		this.p_ptext = p_ptext;
	}
	public Integer getP_pgood() {
		return p_pgood;
	}
	public void setP_pgood(Integer p_pgood) {
		this.p_pgood = p_pgood;
	}
	public String getP_pplate() {
		return p_pplate;
	}
	public void setP_pplate(String p_pplate) {
		this.p_pplate = p_pplate;
	}
	
	
}
