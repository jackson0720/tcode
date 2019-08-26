package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Vip;
import org.sing.tcode.service.VipService;

import com.google.gson.Gson;

@WebServlet("/listvip")
public class ListVipInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VipService service = new VipService();
		List<Vip> v = service.listVipInfo();
		Gson gson = new Gson();
		String json = gson.toJson(v);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		System.out.println(json.toString());
	}
	
	

}
