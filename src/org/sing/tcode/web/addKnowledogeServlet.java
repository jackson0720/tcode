package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.Knowledge;
import org.sing.tcode.entity.User;
import org.sing.tcode.service.KnowledgeService;
import org.sing.tcode.service.UserService;


@WebServlet("/addKnow")
public class addKnowledogeServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
		String name = request.getParameter("txtname");
		int price = (Integer.parseInt(request.getParameter("txtprice")));
		String  title=request.getParameter("txttitle");
		String  tarea = request.getParameter("txtarea");
		KnowledgeService service = new KnowledgeService();
		UserService service1 = new UserService();
		Knowledge k =new Knowledge();
		k.setName(name);
		k.setPrice(price);
		k.setTitle(title);
		k.setRemark(tarea);
		String u = user.getU_account();
		service.addKnowledge(k);
		service1.updateUserPointer(10, u);
        }else {
        	response.sendRedirect("knowledge.html");
        }
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	 
}
