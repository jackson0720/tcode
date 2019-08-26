package org.sing.tcode.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;
import org.sing.tcode.entity.UserInfo;
import org.sing.tcode.service.UserService;
import org.sing.tcode.util.DateUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/addinfo")
public class AddUserInfoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
        	req.setCharacterEncoding("utf-8");
    		String ip = InetAddress.getLocalHost().getHostAddress();
    		String account = req.getParameter("account");
    		String nick = req.getParameter("nick");
    		String tel = req.getParameter("tel");
    		String email = req.getParameter("email");
    		String wechat = req.getParameter("wechat");
    		String qq = req.getParameter("qq");
    		String weibo = req.getParameter("weibo");
    		String dev = req.getParameter("dev");
    		String university = req.getParameter("university");
    		String star = req.getParameter("star");
    		String job = req.getParameter("job");
    		String sex = req.getParameter("sex");
    		String remark = req.getParameter("remark");
    		String idcard = req.getParameter("idcard");
    		String birth = req.getParameter("birthday");
    		String name = req.getParameter("name");
    		UserService service = new UserService();
    		UserInfo u = new UserInfo();
    		u.setU_account(account);
    		u.setU_nick(nick);
    		u.setU_tel(tel);
    		u.setU_email(email);
    		u.setU_wechat(wechat);
    		u.setU_qq(qq);
    		u.setU_weibo(weibo);
    		u.setU_dev(dev);
    		u.setU_university(university);
    		u.setU_constellation(star);
    		u.setU_career(job);
    		u.setSex(sex);
    		u.setU_remark(remark);
    		u.setU_points(0);
    		u.setU_state(1);
    		u.setIfidinfo(0);
    		u.setU_pic("face.png");
    		u.setU_regtime(new Date());
    		u.setU_idinfo("Null");
    		u.setU_groupid(18);
    		u.setU_idcard(idcard);
    		u.setU_name(name);
    		u.setU_birthday(DateUtil.convert(birth));
    		//System.out.println(u);
    		service.addUserInfo(u);
    		service.updateNum(ip, account);
    		service.updateAddUserStates(account);
    		service.updateUserPointer(1500, account);
    		HttpSession session1 = req.getSession();
    		session1.setAttribute("message", "添加成功");
    		String json=new Gson().toJson(session.getAttribute("message"));
    		resp.getWriter().println(json);
        }else {
        	resp.sendRedirect("Login.html");
        }
		
		
	}
	
	

}
