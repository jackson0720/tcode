package org.sing.tcode.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotifyServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> parameterMap=request.getParameterMap();
       
        //业务处理
        for(String key : parameterMap.keySet()) {
        	System.out.println("KEY:"+key+"--value:"+parameterMap.get(key));
        }
        //成功处理后返回success
        response.getWriter().write("success");
    }

}
