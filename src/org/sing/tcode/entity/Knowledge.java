package org.sing.tcode.entity;

public class Knowledge {
	 
	private String name;
	private Integer price;
	private String title;
	private String remark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Knowledge(String name, Integer price, String title, String remark) {
		 
		this.name = name;
		this.price = price;
		this.title = title;
		this.remark = remark;
	}
	public Knowledge() {
		 
	}
	@Override
	public String toString() {
		return "Knowledge [name=" + name + ", price=" + price + ", title=" + title + ", remark=" + remark + "]";
	}
	
}
