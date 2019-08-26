package org.sing.tcode.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.PortService;
import org.sing.tcode.service.PostsService;
import org.sing.tcode.service.ReportService;

@WebServlet("/del_report")
public class DeleteReportServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;setchar=utf-8");
		String po = request.getParameter("pno");
		ReportService service = new ReportService();
		service.deleteReport(po);
	}
}
