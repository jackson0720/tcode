package org.sing.tcode.pay;

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100900648738";
    
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+iQg12kMahFHhQFHoR6hvkDg60yP7Jy+tLLeRP67GgNYGycHlL77QBPp4IYF15mHsaMmIRFirKbHaJD0/zAXF3plYNBhMovkBvcyE1PYxUoYaVJi6E5Pa/0EWAzdMXioNLwC4l9uPGUPOVHGcyLLVsJ4js2vgKJs+zHUu8Dp3WuX9SXNegLCvNEf9Dj8QkES7FoHZ/P2hoNHT6WY9g05CBdlTUcWHRY6p68ZVnzSTFiiyjWQvbQJ1agkGPSFw1MxA/RD8Qa70PEsWbqAo3+ibkglSVyx+/s8EOWn0uxAX4X/Z+OxXfyHV4NMh01xWi/xqs8XIkLUJjCUAOan0IAANAgMBAAECggEAJmq+ywztBsWzNLWhTjJUldoAXGwCDzvBcytW/+RVOxTzEn9PpwRPduXqEpq+3aTMDby7FcyiDDDl+VPhPaHHcDQHuy8Vzy31AxLfz9CYqy02eMyh1rLn1vosh2GynqVF2U6m2JFV5a50a3+/QJt+43MvD4e3soS9eJYE8qfhQfsqauvYVAixmvTTCtAvFmvQJFgBpFECcI4y/f44BfQq6E6LORMerSeC/DKbRnj6LZd5/GrsiSqgCspl375eqaJYMPaLZigPrFVYUtIr2BHD7hYxdUpCUt+B6tw73H5OQhTn/owsVk/Yp9YpkX3rKwIj+b9nhcie0p6P21A7KdlvAQKBgQD0cOEW/kYeJSYVYL3SU0MqciJxBIk2kLDWgoryOuY8nIS3+YM4oVcwsofFFm04Wa94skI38NWvW485cm5JpPxSS8cYwNmyy8FML0WmCDI0KC+ZPTHv1X72D76tUYlzW9riv42Ztjzd5g4+Q+CFGotpNzsHRmRHyS58UEseDRKPLQKBgQDHi5bBWjQH/NCq9xL5atSgxaM9gKljdORJ+FgZLzUYOWlZTv1matQ11JCwq+AF8ErqC768a2YN1UOvfgcOgbQYt4L9wl8nbj5iDYKCC/Th+yafIZax7TtnxL2Po2xMj1QHPcDH8h6tVY5IT17QtPusgT+bV5aK3mMc7hR4aU7AYQKBgQDkB51jhsdAfeU+EiZiW7wPj7OjfNjH50igg908xUddCaIhwgjwvkoJKrLPX/F5nOi9joWJjAi7co10yNQrrVaZKwnLhLRRQBTNjlfJ4GF9GHP23wiDtMWgo+V759FypSDXYFynJUM4udFVsppzcOTWvWg6jCcmdH4CCCFbIlTK7QKBgD6AysflqUaXOgVTPu5BODjjry4kjupIQvmogG/jCY587TK8O0NJUw9JdcjhDVrO2ahV5Xi/a0gJ9RmTMEKq/e6ADNfPel6veXrdIHP7qsjeEkrmCn3RATqPtZC4b8PLYVajUS9P96vzkI21zs0w42Q1V+cgmFcHMaRuLptI4RIBAoGAMjMdz0JPdALZADoETpG7c6TGSKBx4sXGdtwPlceWAZJu001GPfNcckhaERaI3e3++MiTA8wg7+enV168LH3fgjkONhjgwbAi8rxsy3WYcYtvJUwt4sJnO8sMux5d5a4qx8zEtSx4qrd60zDmH+9jx3SDyix1x8wl75y8jdlgp60=";
    
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiYMD7jDw+SmpdiFvmqnPdPeANbGZoGH2tn47lPwfF35rN3UWW8L4YonlvTZNEx4ctSfbHXL8KBUuMnipi84jm114k0TYoJjNjRgA74VoLOtZMAxbmCeU04/iQ9i61Je99mjjGzIA2t0nxjTPesNgByUxZ1sfHkZ0lXHYbzwrvWytOUVCpAHdzdjJh5v9MOSGQLBMMDGiR2Xx7qtoljv90kq0IjIvG9lv5+s9dNFTUZzG/6XD0v5hxOO4IuUbTkGZ2h3RTq7yOnyKPV7GoQcc2f5Yvu/cWEPeWxVrJGC2QJBFDUaW8Okri/AK/fCeqXTEzQY2CobK/LtryqkbTUPNZwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";
    
    // 字符编码格式
    public static String charset = "utf-8";
    
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    
    // 支付宝网关
    public static String log_path = "C:\\";

}
