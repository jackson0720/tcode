package org.sing.tcode.util;

import java.util.*;

import org.sing.tcode.entity.Port;
import org.sing.tcode.entity.Posts;

public class PageBean2<T> {
	//当前页
	private Integer pageNum;
	//每页显示多少条数
	private Integer pageSize;
	//总记录数
	private Integer total;
	//总共分多少页数
	private Integer pageCount;
		
	public PageBean2(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	/**
	 * 封装结果集
	 */
	private List<Posts> list;
	
	private List<Port> port;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		pageNum = pageNum<=0?1:pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * 计算总页数
	 * @return
	 */
	public Integer getPageCount() {
		this.pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		return pageCount;
	}
	/**
	 * 根据页面传过来的pageNum（当前页）
	 * 计算出处从数据库的第几条开始查询
	 * 当前页面*每页显示的记录数
	 * @return
	 */
	public Integer getPageRow() {
		 return  (pageNum-1)*pageSize+1;
	}
	public Integer getFectyRow() {
		return getPageRow()+pageSize-1;
	}
	
	public List<Posts> getList() {
		return list;
	}
	public void setList(List<Posts> list) {
		this.list = list;
	}
	
	public List<Port> getPortList() {
		return port;
	}
	public void setPortList(List<Port> port) {
		this.port = port;
	}
}
