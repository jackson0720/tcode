package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.VipBuy;
import org.sing.tcode.service.BuyService;

import com.google.gson.Gson;

@WebServlet("/addVip")
public class AddVipBuyServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		VipBuy o = (VipBuy) req.getSession().getAttribute("vipresult");
		String user = (String)req.getSession().getAttribute("account");
		String oid = (String)req.getSession().getAttribute("oid");
        BuyService service = new BuyService();
        service.insertBuy(oid,user,o.getVbtitle(),o.getVbtext(),o.getVbprice());
        req.setAttribute("message", "购买成功！");
        Gson gson = new Gson();
		String json = gson.toJson(req.getAttribute("message"));
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().print(json);
	}
	
}
