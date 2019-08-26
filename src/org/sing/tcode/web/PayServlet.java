package org.sing.tcode.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sing.tcode.pay.AlipayConfig;

import com.alipay.api.AlipayClient;
import com.alipay.api.*;
import com.alipay.api.request.*;

@WebServlet("/pay")
public class PayServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	      //设置请求参数
	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        alipayRequest.setReturnUrl(AlipayConfig.return_url);
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

	        //商户订单号，商户网站订单系统中唯一订单号，必填
	        String out_trade_no = "20181037";
	        //付款金额，必填
	        String total_amount = "48.1509";
	        //订单名称，必填
	        String subject = "Java:Java高并发之魂：synchronized深度解析";
	        //商品描述，可空
	        String body = "高并发问题向来是Java程序员进阶的重点，也是面试的难点。想要打通高并发的奇经八脉，Synchronized是你不得不趟过的坑，本课程从Synchronized，从使用方法到底层原理源码，娓娓道来。";

	        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
	                + "\"total_amount\":\""+ total_amount +"\"," 
	                + "\"subject\":\""+ subject +"\"," 
	                + "\"body\":\""+ body +"\"," 
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

	        //请求
	        String result;
			try {
				result = alipayClient.pageExecute(alipayRequest).getBody();
				resp.setContentType("text/html;charset=" + AlipayConfig.charset);
				resp.getWriter().write(result);//直接将完整的表单html输出到页面
				resp.getWriter().flush();
				resp.getWriter().close();
			} catch (AlipayApiException e) {
				e.printStackTrace();
				resp.getWriter().write("捕获异常出错");
				resp.getWriter().flush();
				resp.getWriter().close();
			}
	}
	
	

}
