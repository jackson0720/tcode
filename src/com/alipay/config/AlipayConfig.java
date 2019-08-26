package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100900648738";
    
    // 商户私钥，您的PKCS8格式RSA2私钥
	//商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPV10K8KOoO4lnYEKYb3NNw1550oM+SaNrWQA5IyUx5ktlNhukHAc7Mg7kEi/mwkXgG31kSwzCcAhZZMoz/XZqqSCG+YvlVxeuKRDEyZb9OV8KPn8CrcpwaKo+LkHBpBbrdRO9Vf1ShnU7xOzD9G1hxMEtT46WPF+FTWqv6PnKigGsWMP/Qblxb2ml43vFU4cPRFblWkWcgitfblTNdv52w4uiNo2vrsKAtI+L9uIOUcxQ6yJ/X1f6O9Xwv1jgBOKSFkk5XcOk6TdCVQxKpKqHpxK3xfxxeS5h4iLTUaqr1a212nXd/fuaJNFqoatyVgAwekF1Llrw3cykzoC3+ysZAgMBAAECggEAR/Qw7obxgEeyp8P4ahC4Lrb9u+VioGfSstZQeF4eE7ho7HuG0JCJSLvOIcrHZOzsqpziCvBpjUkzx7wCjKqVQOF5RIgMI6neZzySAFa5ho/AF6Jbm3ql/ltr6h7KP2whXTy+FfMOYNjCDKjDuXH/Xm6oEhi3mM7hnEbKYVdv3K0198Zuh6BjGq1JJZFtl1C/ZYyfV5S+RJ8/AoLON8HqxZpKboB6QjBWn9NSi7uhYoeKeG4S/wWxDESqqSRocjPmwjnGtxXxDq7Tq6vJ7L+y+84GAUvZMDKv/5MuWfUZYmTcwR/yuBylqYw4tfGR7hotdWMuKAN107OV1sYaQigdAQKBgQD/I81sktwqD9n1oztuxqMzpvhKHVNKY7eVeSufZLG6BKbfYXrPqUJq2/NGGVCvSCcWwpwka81Gmyqf9bT6eNmpvtXKO9TIJ6s2I5Z0dytY2obZK23B/GIWbYXSbjPqzMiu/hOS8p8qcGk1uILOU719ZLQuF2PB7u2BmmY7CNKfSQKBgQCP0xLZMHySjD1Z14a0iwkb0CqFDXhIc5/OFa7qDi0CBofPX3qykLqudEKlUznCq7O8Y210SVIaMi9df0nqr3kX0IYyUa41ZS6r/Da+mwNp9XTAFRGpKJEPsDsmOar5wUa9xcNG5bXVPTQQ1tEIa0ZrDMLLnEfmEVnQydn0KW+dUQKBgGG8REfwJhiSw+q94dl4jDIQJxK1Ex/u/PdsWFKRzycz+vydKd98ti2bHiDfN7I9kIO+fvPQu+ztKbHm/LHqM2875Ee5DbSyxXpSvaLVt6ws4fU1FrF1zdlLkTTf9FFgqwul1re261PlhdgHW0xQdckgF4mEejljHmZEUB8TLSPJAoGAZLwDwZdSSRpshhaMxLt0/8QHY/WRP72sOh25yzlukLKeQMKxH4x7FGFdpInsNMsqMlK5gO8x0CNCRYw362ZHvERTGb9X8xIRZGhct5e/BmHjCf43xnEhWQmoBBf0BsY2lAiFw4x0lQwvAXRUKD9qrOPdM9scCKoybChUvcyf3mECgYA3WFQtKxBZ7Or98mZXysQ5vDlk2k9weF0jxFYBvxo66UC464iK0V61t3Dg65EGIpXqPAFzZZNq/HFRIK825T75BYwWqvik9805O8+zYYmxZ3g1FJ2nmCf3MXsYfWnjJT3y96q8zAckjk937blSvqRnS4IThSv5mJ70K6FlnHX9yA==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj1ddCvCjqDuJZ2BCmG9zTcNeedKDPkmja1kAOSMlMeZLZTYbpBwHOzIO5BIv5sJF4Bt9ZEsMwnAIWWTKM/12aqkghvmL5VcXrikQxMmW/TlfCj5/Aq3KcGiqPi5BwaQW63UTvVX9UoZ1O8Tsw/RtYcTBLU+OljxfhU1qr+j5yooBrFjD/0G5cW9ppeN7xVOHD0RW5VpFnIIrX25UzXb+dsOLojaNr67CgLSPi/biDlHMUOsif19X+jvV8L9Y4ATikhZJOV3DpOk3QlUMSqSqh6cSt8X8cXkuYeIi01Gqq9Wttdp13f37miTRaqGrclYAMHpBdS5a8N3MpM6At/srGQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/NotifyServlet";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/tcode/result.html";

    // 签名方式
    public static String sign_type = "RSA2";
    
    // 字符编码格式
    public static String charset = "utf-8";
    
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    
    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

