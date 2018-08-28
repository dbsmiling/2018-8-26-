package com.backstage.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.mysql.cj.api.Session;

import net.sf.json.JSONObject;

/**
 * @author 嘉如新上人
 * 图片文件上传
 */
public class BackstageCommodityupload extends HttpServlet{

	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");//编码格式
	      RequestContext requestContext = new ServletRequestContext(request);//获取request
	      System.out.println("进入post上传图片");
	      if(FileUpload.isMultipartContent(requestContext)){//判断是不是enctype="multipart/form-data"
	            DiskFileItemFactory factory = new DiskFileItemFactory();//使用commons-fileupload这个JAR包
	            factory.setRepository(new File("d:/tmp/"));//设置一个临时文件路径
	            ServletFileUpload upload = new ServletFileUpload(factory);//ServletFileUpload 上传
	            upload.setSizeMax(2000000);//设置最大上传限制
	            List list = new ArrayList();//
	            try {
	            	list = upload.parseRequest(request);//文件集合
	            } catch (FileUploadException e1) {
	               System.out.println("文件上传发生错误" + e1.getMessage());
	            }
	            Iterator it = list.iterator();//把list给转成迭代
	            while(it.hasNext()){
	                FileItem fileItem = (FileItem) it.next();//file-upload中的一个类FileItem
	                if(fileItem.isFormField()){      
	                       System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String
	                                           (fileItem.getString().getBytes("iso8859-1"), "gbk"));//输出一波
	                }else{
	                       System.out.println(fileItem.getFieldName() + "   " + 
	                               fileItem.getName() + "   " + fileItem.isInMemory() + "    " + 
	                               fileItem.getContentType() + "   " + fileItem.getSize());//输出一波
	                       	//判断文件是否为空
	                       if(fileItem.getName()!=null && fileItem.getSize()!=0){
	                               File fullFile = new File(fileItem.getName());
	                               String name = String.valueOf(getRandNum(1000,9999))+".png";
	                               //"C:/Users/Administrator/eclipse-workspace/WYYX/WebContent/img/"
	                               File newFile= new File("C:/Users/Administrator/eclipse-workspace/WYYX/WebContent/img/"+name);
	                               File oldfile = new File("D:/tmp/");
	                               if(newFile.exists()) {//检查文件是否存在
	                            	   newFile.delete();
	                            	   newFile.createNewFile();
	                            	   
	                               }else {
	                            	   newFile.createNewFile();
	                            	   
	                               }
	                               try {
	                                   fileItem.write(newFile);
	                                   oldfile.delete();
	                            	   System.out.println(name);
	                            	   request.setAttribute("uploadname",name);
	                            	   HttpSession session = request.getSession();
	                            	   session.setAttribute("uploadname", name);
	                            	   request.getRequestDispatcher("tz.html").forward(request, response);;
	                            	   
	                               } catch (Exception e) {
	                                      e.printStackTrace();
	                               }
	                       }else{
	                               System.out.println("文件没有选择 或 文件内容为空");
	                       }
	                }
	            }
	      }else {
	    	  
	      }
		
	}
}
