package org.sing.tcode.entity;

/**
 * 代码仓库表code详细信息
 * @author Aidan
 *
 */
public class Code {

	private String c_no;		//仓库编号
	private String c_account;	//仓库持有者
	private String c_position;  //仓库所在路径
	private String c_size;		//仓库总大小
	
	public String getC_no() {
		return c_no;
	}
	public void setC_no(String c_no) {
		this.c_no = c_no;
	}
	public String getC_account() {
		return c_account;
	}
	public void setC_account(String c_account) {
		this.c_account = c_account;
	}
	public String getC_position() {
		return c_position;
	}
	public void setC_position(String c_position) {
		this.c_position = c_position;
	}
	public String getC_size() {
		return c_size;
	}
	public void setC_size(String c_size) {
		this.c_size = c_size;
	}
	
	
	
}
