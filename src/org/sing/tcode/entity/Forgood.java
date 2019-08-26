package org.sing.tcode.entity;

/**
 * 点赞表forgood详细信息
 * @author Aidan
 *
 */
public class Forgood {

	private Integer fg_no;		//点赞编号
	private String fg_pid;		//帖子ID
	private String fg_account;	//用户账号
	private Integer fg_good;	//是否点赞
	
	public Integer getFg_no() {
		return fg_no;
	}
	public void setFg_no(Integer fg_no) {
		this.fg_no = fg_no;
	}
	public String getFg_account() {
		return fg_account;
	}
	public void setFg_account(String fg_account) {
		this.fg_account = fg_account;
	}
	public String getFg_pid() {
		return fg_pid;
	}
	public void setFg_pid(String fg_pid) {
		this.fg_pid = fg_pid;
	}
	public Integer getFg_good() {
		return fg_good;
	}
	public void setFg_good(Integer fg_good) {
		this.fg_good = fg_good;
	}
	
	
}
