package com.wu.Servalet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.ndktools.javamd5.Mademd5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class gerVer
 */
@WebServlet("/gerVer")
public class gerVer extends HttpServlet {
	 private static final char[] chars = {
		        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		    // 字符数量
		    private static final int SIZE = 6;
		    // 干扰线数量
		    private static final int LINES = 5;
		    // 宽度
		    private static final int WIDTH = 130;
		    // 高度
		    private static final int HEIGHT = 50;
		    // 字体大小
		    private static final int FONT_SIZE = 24;
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		gerVer gV=new gerVer();
		Object[] objs =gV.createImage();
        BufferedImage image = (BufferedImage) objs[1];
        String name=(String) objs[0];
       // System.out.println(name);
        Date dt=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); 
        String time=df.format(dt);
        String path="F://eclipse-workspace/wu01/WebContent/Ver"+"/"+time+".jpg";
        OutputStream os = new FileOutputStream(path);
        ImageIO.write(image, "jpeg", os);
        os.close();
		
        System.out.println(path);
        String urlPath=path;
        int beginIndex=0;
        int endIndex=0;
        beginIndex =urlPath.indexOf("V");
		endIndex = urlPath.lastIndexOf("g")+1;
		String newPath=urlPath.substring(beginIndex,endIndex);
		System.out.println(newPath);
		Map<String, String> map=new HashMap<>();
		map.put("code", name);
		map.put("path",newPath);
		JSONObject jsob=JSONObject.fromObject(map);
        response.getWriter().write(jsob.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String phoneNumber=request.getParameter("phoneNumber");
		System.out.println(phoneNumber);
		//获取验证码的手机号
		gerVer gV=new gerVer();
		String key=getRandNum(100000,999999)+"";
		//6位验证码
		String backcode=getPhone(phoneNumber, key);
		JSONObject  myJson = JSONObject.fromObject(backcode);
		Map m=myJson;
		String coder=m.get("respCode").toString();
		Map<String, String> map=new HashMap<>();
		map.put("key", key);
		map.put("backcode", coder);
		JSONObject jsob=JSONObject.fromObject(map);
		System.out.println(jsob);
		response.getWriter().write(jsob.toString());
		
	}
	
	
	     
	    // 验证码字符集
	   
	 
	    /**
	     * 生成随机验证码及图片
	     */
	    public Object[] createImage() {
	        StringBuffer sb = new StringBuffer();
	        // 1.创建空白图片
	        BufferedImage image = new BufferedImage(
	            WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        // 2.获取图片画笔
	        Graphics graphic = image.getGraphics();
	        // 3.设置画笔颜色
	        graphic.setColor(Color.LIGHT_GRAY);
	        // 4.绘制矩形背景
	        graphic.fillRect(0, 0, WIDTH, HEIGHT);
	        // 5.画随机字符
	        Random ran = new Random();
	        for (int i = 0; i <SIZE; i++) {
	            // 取随机字符索引
	            int n = ran.nextInt(chars.length);
	            // 设置随机颜色
	            graphic.setColor(getRandomColor());
	            // 设置字体大小
	            graphic.setFont(new Font(
	                null, Font.BOLD + Font.ITALIC, FONT_SIZE));
	            // 画字符
	            graphic.drawString(
	                chars[n] + "", i * WIDTH / SIZE, HEIGHT / 2);
	            // 记录字符
	            sb.append(chars[n]);
	         //   System.out.println(sb.toString());
	        }
	        // 6.画干扰线
	        for (int i = 0; i < LINES; i++) {
	            // 设置随机颜色
	            graphic.setColor(getRandomColor());
	            // 随机画线
	            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),
	                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
	        }
	        // 7.返回验证码和图片
	        return new Object[]{sb.toString(), image};
	    }
	 
	    /**
	     * 随机取色
	     */
	    public Color getRandomColor() {
	        Random ran = new Random();
	        Color color = new Color(ran.nextInt(256),
	                ran.nextInt(256), ran.nextInt(256));
	        return color;
	    }
	    
	    public String getPhone(String phoneNumber,String key) {
	    	String line = null;
	    	HttpURLConnection connection = null;  
	    	BufferedReader bf = null;
	    	StringBuilder sb = null;
	    	try {
	    		URL url = new URL("https://api.miaodiyun.com/20150822/industrySMS/sendSMS");  
	    		 connection = (HttpURLConnection) url.openConnection();
	    		 connection.setDoOutput(true);  
	    		 connection.setDoInput(true);  
	    		 connection.setRequestMethod("POST");  
	    		 connection.setUseCaches(false);  
	    		 connection.setInstanceFollowRedirects(true);  
	    		 connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");     
	    		 connection.connect();  
	    		 
	    		 Date dt=new Date();
	    		 SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
	    		 String dttime=df.format(dt);
	    		 Mademd5 md = new Mademd5();
	    		 
	    		 //随机生成的验证码
	    		 
	    		 String appId="20de5c6bb9d34c3d9d0979646d4ad1ef";
	    		 String token="d0eed09c90394b23b938c314f6ec4705";
	    		 String sigs=md.toMd5(appId+token+dttime);
	    		 
	    		 
	    		 DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());  
	    		 String accountSid = "accountSid="+ URLEncoder.encode("20de5c6bb9d34c3d9d0979646d4ad1ef", "utf-8"); 
	    		 String smsContent = "&templateid="+ URLEncoder.encode("529594296", "utf-8");
	    		 String parm="&param="+URLEncoder.encode(key, "utf-8");
	    		 String phoneNum = "&to="+URLEncoder.encode(phoneNumber,"utf-8");
	    		 String time="&timestamp="+URLEncoder.encode(dttime, "utf-8");
	    		 String sig="&sig="+URLEncoder.encode(sigs.toLowerCase(),"utf-8");
	    		 String respDataType="&respDataType="+URLEncoder.encode("JSON", "utf-8"); 
	    		 String parms = accountSid+smsContent+parm+phoneNum+time+sig+respDataType;  
	    		 // 将参数输出到连接  
	    		 dataout.writeBytes(parms);  
	    		 // 输出完成后刷新并关闭流  
	    		 dataout.flush();  
	    		 dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)   
	             System.out.println(connection.getResponseCode());
	    		 
	    		 bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));   
	    		   
	    		 sb = new StringBuilder();
	    		 while ((line = bf.readLine()) != null) {        
	    		     sb.append(line).append(System.getProperty("line.separator"));  
	    		 }
	    	} catch (MalformedURLException e) {
	    		e.printStackTrace();
	    	} catch (ProtocolException e) {
	    		e.printStackTrace();
	    	} catch (UnsupportedEncodingException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}finally{
	    		try {
	    			bf.close();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		connection.disconnect(); // 销毁连接  
	    	}
	    		
	    	}
	    	String backcode=sb.toString();//获取短信接口的返回状态码
	    	System.out.println(backcode);
	    	return backcode;
	    	}
	    
	    public static int getRandNum(int min, int max) {
		    int randNum = min + (int)(Math.random() * ((max - min) + 1));
		    return randNum;
		}
	}

