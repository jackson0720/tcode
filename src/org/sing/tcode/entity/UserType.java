package org.sing.tcode.entity;

/**
 * 用户组表usertype 详细信息		
 * @author Aidan
 *
 */
public class UserType {

	private Integer utype_no;	//用户组序号
	private Integer utype_id;	//用户组ID
	private String utype_name;	//用户组名称
	private String type_admin;	//用户组权限
	private String type_desc; 	//用户组简述
	
	
	public Integer getUtype_no() {
		return utype_no;
	}
	public void setUtype_no(Integer utype_no) {
		this.utype_no = utype_no;
	}
	public String getType_admin() {
		return type_admin;
	}
	public void setType_admin(String type_admin) {
		this.type_admin = type_admin;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	public Integer getUtype_id() {
		return utype_id;
	}
	public void setUtype_id(Integer utype_id) {
		this.utype_id = utype_id;
	}
	public String getUtype_name() {
		return utype_name;
	}
	public void setUtype_name(String utype_name) {
		this.utype_name = utype_name;
	}
	
}
