package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Buy;
import org.sing.tcode.service.BuyService;

import com.google.gson.Gson;

@WebServlet("/listbuy")
public class ListBuyServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		BuyService service = new BuyService();
		List<Buy> buy = service.listBuyByUserAccount(account);
		Gson gson = new Gson();
		String json = gson.toJson(buy);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
		//System.out.println(json.toString());
	}
	
	

}
