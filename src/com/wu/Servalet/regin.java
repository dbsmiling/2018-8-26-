package com.wu.Servalet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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

import net.sf.json.JSONObject;

/**
 * Servlet implementation class regin
 */
@WebServlet("/regin")
public class regin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String myEmailAccount = "508168274@qq.com";//�Լ�������˻�
	public static String EmaiPHost = "smtp.qq.com";//�����������ַ
    public static String smtpPort="465";//����������˿�
    String VerificationCode=null;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
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
		        // 3. ����һ���ʼ�
		        MimeMessage message = null;
				try {
					message = createMail(session, myEmailAccount, email);
				} catch (MessagingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        // 4. ���� Session ��ȡ�ʼ��������
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
					
					e.printStackTrace();
				}
		        try {
					transport.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Map<String,String> map=new HashMap<>();
		        map.put("code","01");
		        map.put("value", VerificationCode);
		        JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
		        
	}

	private MimeMessage createMail(Session session, String myEmail, String email) throws UnsupportedEncodingException, MessagingException {
				
					MimeMessage message = new MimeMessage(session);
					
			        message.setFrom(new InternetAddress(myEmail, "My Home", "utf-8"));
			      
			        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, email, "utf-8"));
			        
			        
			        message.setSubject("欢迎注册Leaf建材", "utf-8");
			        
			    
			        message.setContent("<h1>您的验证码是"+VerificationCode+"--Leaf建材</h1>", "text/html;charset=utf-8");
			       
			       
			        message.setSentDate(new Date());
		  
			     
			        message.saveChanges();
			        
			        return message;
	}

}
