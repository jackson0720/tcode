package org.sing.tcode.entity;

import java.sql.Date;

/**
 * 论坛附件表bbs_attachment
 * @author Aidan
 *
 */
public class Attachment {

	private Integer id;			//ID标识	
	private Integer topicid;	//帖子ID
	private String name;		//附件名称
	private String filepath;    //附件地址ַ
	private Integer point;		//下载所需积分
	private String description; //描述
	private Integer utype_id;	//投票者ID
	private Integer counts;		//附件被下载的总次数
	private Boolean isenabled;	//是否启用
	private Date creaeon;		//上传时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTopicid() {
		return topicid;
	}
	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getUtype_id() {
		return utype_id;
	}
	public void setUtype_id(Integer utype_id) {
		this.utype_id = utype_id;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Boolean getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(Boolean isenabled) {
		this.isenabled = isenabled;
	}
	public Date getCreaeon() {
		return creaeon;
	}
	public void setCreaeon(Date creaeon) {
		this.creaeon = creaeon;
	}
	
	
}
