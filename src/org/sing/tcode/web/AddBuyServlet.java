package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Order;
import org.sing.tcode.entity.VipBuy;
import org.sing.tcode.service.BuyService;
import org.sing.tcode.service.UserService;
import org.sing.tcode.service.VipBuyService;

import com.google.gson.Gson;

@WebServlet("/addbuy")
public class AddBuyServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer pid = (Integer) req.getSession().getAttribute("pid");
		if(pid == 12) {
			Order o = (Order) req.getSession().getAttribute("result");
			String user = (String)req.getSession().getAttribute("account");
			BuyService service = new BuyService();
	        service.insertBuy(o.getOrderid(), user, o.getOrdername(), o.getMessage(), o.getOrderpay());
	        req.setAttribute("message", 12);
	        Gson gson = new Gson();
			String json = gson.toJson(req.getAttribute("message"));
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(json);
		}else if(pid == 11) {
			VipBuy o = (VipBuy) req.getSession().getAttribute("result");
			String user = (String)req.getSession().getAttribute("account");
			VipBuyService service = new VipBuyService();
			UserService service1 = new UserService();
			service.insertBuy(o.getVborderid(), user, o.getVbtitle(), o.getVbtext(), o.getVbprice());
			service1.updateUserGroupid(user);
			service1.updateUserPointer(30, user);
			service1.updateVipMonthDate(user);
			req.setAttribute("msg", 11);
	        Gson gson = new Gson();
			String json = gson.toJson(req.getAttribute("message"));
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(json);
		}
        
	}
	
	

}
