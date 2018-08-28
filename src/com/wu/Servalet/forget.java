package com.wu.Servalet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.Service.LoginService;
import com.wu.Service.LoginServiceimp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class forget
 */
@WebServlet("/forget")
public class forget extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String myEmailAccount = "508168274@qq.com";//定义发送者的邮箱地址
	public static String EmaiPHost = "smtp.qq.com";//定义邮箱服务器ַ
    public static String smtpPort="465";//邮箱服务器端口
    String VerificationCode=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map=new HashMap<>();
		String email=request.getParameter("email");
		LoginService lService=new LoginServiceimp();
		int i= lService.Loginquery(email);
		if (i!=1) {
			map.put("code", "0003");
			JSONObject jsob=JSONObject.fromObject(map);
	        response.getWriter().write(jsob.toString());
		}else {
			VerificationCode=getRandNum(100000,999999)+"";
			
			Properties prop=new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", EmaiPHost);
			prop.setProperty("mail.smtp.auth", "true");//这是一堆配置的参数֤
			//ssl֤
			prop.setProperty("mail.smtp.port", smtpPort);
	        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
	        prop.setProperty("mail.smtp.socketFactory.port", smtpPort);
	        
	        Session session = Session.getDefaultInstance(prop);
	        //建立session连接
	        MimeMessage message = null;
			try {
				message = createMail(session, myEmailAccount, email);
			} catch (MessagingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        // 4.发送邮件
	        Transport transport = null;
			try {
				transport = session.getTransport();
			} catch (NoSuchProviderException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        try {
				transport.connect(myEmailAccount, "qsivhcimdmxcbhbe");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				transport.sendMessage(message, message.getAllRecipients());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				transport.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        map.put("code","0000");
	        map.put("value", VerificationCode);
	        JSONObject jsob=JSONObject.fromObject(map);
	        response.getWriter().write(jsob.toString());
	        
}
	        
		}
	private MimeMessage createMail(Session session, String myEmail, String email) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		
        message.setFrom(new InternetAddress(myEmail, "My Home", "UTF-8"));
      
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, email, "UTF-8"));
        
        
        message.setSubject("欢迎注册Leaf建材", "UTF-8");
        
    
        message.setContent("<h1>您的找回验证码是"+VerificationCode+"，如果不是本人操作请忽略此邮件--Leaf建材</h1>", "text/html;charset=UTF-8");
       
       
        message.setSentDate(new Date());

     
        message.saveChanges();
        
        return message;
	}
		
		
		
		
	

}
