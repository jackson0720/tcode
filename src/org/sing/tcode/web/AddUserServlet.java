package org.sing.tcode.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.ResponseVO;
import org.sing.tcode.entity.User;
import org.sing.tcode.exception.DaoException;
import org.sing.tcode.exception.UserException;
import org.sing.tcode.service.UserService;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

@WebServlet("/register")
public class AddUserServlet extends BasicServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		ResponseVO vo = null;
		PrintWriter pw = resp.getWriter();
		String userName = req.getParameter("userName");		
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		Integer num = 0;
		String ip = InetAddress.getLocalHost().getHostAddress();
		//获取表单提交的验证码
		String code = req.getParameter("code");
		User user = new User();
		user.setU_account(userName);
		user.setU_password(password);
		user.setU_email(email);
		user.setU_IP(ip);
		user.setU_state(1);
		user.setU_num(num);
		//验证账号密码
		UserService service = new UserService();
		
		System.out.println(userName);
		try {
			service.getUserByAccount(userName, email);
			String sessionCode = (String)req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			  if(!code.equals(sessionCode)) {
				  vo=err(401, "验证码错误");
				  //throw new UserException("验证码错误");
				  
			  }else {
				  service.addUser(user);
					vo=success(200);
			  }
			//resp.sendRedirect("index.html");
		}catch(UserException e) {
			  e.printStackTrace();
			  vo=err(401,e.getMessage());
			  System.out.println(e.getMessage());
		}
		String json=new Gson().toJson(vo);
		pw.println(json);
	}
	
	

}
