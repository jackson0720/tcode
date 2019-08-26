package org.sing.tcode.web;

import javax.servlet.http.HttpServlet;

import org.sing.tcode.entity.ResponseVO;


public class BasicServlet extends HttpServlet{
	protected ResponseVO success(int code,Object data) {
		ResponseVO vo=new ResponseVO();
		vo.setCode(code);
		vo.setData(data);
		return vo;
	}
	
	protected ResponseVO success(int code) {
		ResponseVO vo=new ResponseVO();
		vo.setCode(code);
		return vo;
	}
	
	protected ResponseVO success(Object data) {
		ResponseVO vo=new ResponseVO();
		vo.setData(data);
		return vo;
	}
	
	
	protected ResponseVO err(Integer code,String message) {
		ResponseVO vo=new ResponseVO();
		vo.setCode(code);
		vo.setMessage(message);
		return vo;
	}
	
	
	protected ResponseVO err(Integer code) {
		ResponseVO vo=new ResponseVO();
		vo.setCode(code);
		return vo;
	}
	
	

}

