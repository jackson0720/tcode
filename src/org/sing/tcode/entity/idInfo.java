package org.sing.tcode.entity;

/**
 * 实名认证表 idinfo详细信息
 * @author Aidan
 *
 */
public class idInfo {

	private Integer info_no;		//认证编号
	private Integer info_code;		//实名认证码
	private String info_name;		//身份证名字
	private String info_number;		//证件号码
	private String info_position;	//正面所在路径
	private String info_positionb;	//反面所在路径
	private String info_address;	//居住地
	public Integer getInfo_no() {
		return info_no;
	}
	public void setInfo_no(Integer info_no) {
		this.info_no = info_no;
	}
	public Integer getInfo_code() {
		return info_code;
	}
	public void setInfo_code(Integer info_code) {
		this.info_code = info_code;
	}
	public String getInfo_name() {
		return info_name;
	}
	public void setInfo_name(String info_name) {
		this.info_name = info_name;
	}
	public String getInfo_number() {
		return info_number;
	}
	public void setInfo_number(String info_number) {
		this.info_number = info_number;
	}
	public String getInfo_position() {
		return info_position;
	}
	public void setInfo_position(String info_position) {
		this.info_position = info_position;
	}
	public String getInfo_positionb() {
		return info_positionb;
	}
	public void setInfo_positionb(String info_positionb) {
		this.info_positionb = info_positionb;
	}
	public String getInfo_address() {
		return info_address;
	}
	public void setInfo_address(String info_address) {
		this.info_address = info_address;
	}
	
	
}
