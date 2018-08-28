package com.silence.Servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

@WebServlet("/FileLoac")
public class FileLoac extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imgname=null;
		System.out.println("file-post-请求");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		RequestContext requestContext = new ServletRequestContext(request);//获取request
		if(FileUpload.isMultipartContent(requestContext)) {//判断是不是enctype="multipart/form-data"
			
			DiskFileItemFactory factory = new DiskFileItemFactory();//使用commons-fileupload这个JAR包
			factory.setRepository(new File("d:/tmp/"));//设置一个临时文件路径
			
			ServletFileUpload upload = new ServletFileUpload(factory);//ServletFileUpload 上传
            upload.setSizeMax(2000000*100);//设置最大上传限制
            
            List list = new ArrayList();
            try {
            	list = upload.parseRequest(request);//文件集合
            } catch (FileUploadException e1) {
               System.out.println("文件上传发生错误" + e1.getMessage());
            }
            Iterator it = list.iterator();//把list给转成迭代
            while(it.hasNext()){
                FileItem fileItem = (FileItem) it.next();//file-upload中的一个类FileItem
                System.out.println("图片名称："+fileItem.getName());
                imgname=fileItem.getName();
                if(fileItem.isFormField()){   
                	
                	System.out.println(imgname+"1111111");
                    System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String
                                           (fileItem.getString().getBytes("iso8859-1"), "gbk"));//输出一波
                }else{
                   System.out.println(fileItem.getFieldName() + "   " + 
                           fileItem.getName() + "   " + fileItem.isInMemory() + "    " + 
                           fileItem.getContentType() + "   " + fileItem.getSize());//输出一波
                   	//判断文件是否为空
                   if(fileItem.getName()!=null && fileItem.getSize()!=0){
                           File fullFile = new File(fileItem.getName());
                           File newFile = new File("d://" + fullFile.getName());
                           if(newFile.exists()) {//检查文件是否存在
                        	   newFile.delete();
                        	   newFile.createNewFile();
                           }else {
                        	   newFile.createNewFile();
                           }
                           try {
                               fileItem.write(newFile);
                              
                           } catch (Exception e) {
                                  e.printStackTrace();
                           }
                   }else{
                           System.out.println("文件没有选择 或 文件内容为空");
                   }
                }
            }
		}else {
			System.out.println("上传失败");
		}
		Map img = new HashMap();
		img.put("url", imgname);
		System.out.println(img+"这个是jsonarr");
		response.getWriter().write(img.toString());
		
	}

}
