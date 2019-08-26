package org.sing.tcode.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sing.tcode.entity.User;
import org.sing.tcode.entity.Vip;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.config.AlipayConfig;

public class VipServlet extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		Random random = new Random();
	    SimpleDateFormat allTime = new SimpleDateFormat("YYYYMMdd");
	    String subjectno = allTime.format(new Date())+random.nextInt(10);       
	    String no = subjectno+random.nextInt(10);
		
		System.out.println("开始处理VipServlet的服务");
		String name = request.getParameter("vname");
        String price = request.getParameter("vprice");
        String date = request.getParameter("vdate");
        String text = request.getParameter("vtext");
        String account = request.getParameter("account");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String oid = no;
        if(user != null) {
            Vip v = new Vip();
            v.setVname(name);
            v.setVprice(price);
            v.setVdate(date);
            v.setVtext(text);
            
            //向支付宝发送请求
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
                    AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
                    AlipayConfig.sign_type);
                    
            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = no+"";
            //付款金额，必填
            String total_amount = v.getVprice() + v.getVdate();
            //订单名称，必填
            String subject = v.getVname();
            //商品描述，可空
            String body = v.getVtext();
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
                    + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            
            AlipayTradePagePayResponse alipayResponse = null;
            try {
                alipayResponse=alipayClient.pageExecute(alipayRequest);
                System.out.println(alipayResponse.getBody());
                System.out.println(alipayResponse.getMsg());
            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
            
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(alipayResponse.getBody());
            session.setAttribute("vipresult", v);
            session.setAttribute("account", account);
            session.setAttribute("oid", oid);
        }else {
        	response.sendRedirect("Login.html");
        }
        
    }
	
}
