package org.sing.tcode.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.entity.Knowledge;
import org.sing.tcode.service.KnowledgeService;

import com.google.gson.Gson;
@WebServlet("/listKnow")
public class ListKnowledogeServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KnowledgeService service = new KnowledgeService();
	 List<Knowledge> list = service.listKnowledge();
	 String json = new Gson().toJson(list);
	 response.setContentType("application/json;charset=utf-8");
	 response.getWriter().print(json);
 
	}
}
