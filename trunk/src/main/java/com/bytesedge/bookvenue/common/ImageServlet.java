package com.bytesedge.bookvenue.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytesedge.bookvenue.core.ServerConfig;
import com.bytesedge.bookvenue.model.Context;
import com.bytesedge.bookvenue.util.StringUtil;

@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(ImageServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serveImage(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serveImage(request, response);	
	}
	
	public void serveImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		OutputStream outStream = response.getOutputStream();
		FileInputStream fin = null;
		try {
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			String fileType = request.getParameter("type");
			if(!StringUtil.isEmpty(fileType)) {
				response.setContentType(fileType);
			} else if(request.getRequestURI().endsWith("gif")) {
				response.setContentType("image/gif");
			} else if(request.getRequestURI().endsWith("jpg")) {
				response.setContentType("image/jpg");
			} else if(request.getRequestURI().endsWith("jpeg")) {
				response.setContentType("image/jpeg");
			} else {
				response.setContentType("image/png");
			}
			
			// Add a Parameter to HttpSession
			HttpSession session = (HttpSession)request.getSession();
			String ctxCode = (String) session.getAttribute("ctxCode");
			if(ctxCode == null) {
				// Try to load it from the DB/Cache
				Context context = CacheService.getContextByUrl(request.getServerName());
				
				// check ctxCode
				if(context == null || context.getCode() == null) {
					throw new IOException("Not valid Org");
				}
				ctxCode = context.getCode();
			}
			
			outStream = response.getOutputStream();
			
			File file = new File(ServerConfig.DIR_DATA_REPO, ctxCode + File.separator + URLDecoder.decode(request.getRequestURI(),"utf-8"));
			
			if((file != null) && !file.isDirectory() && file.exists()) {
				fin = new FileInputStream(file);
				byte b[] = new byte[1024];
				while(fin.available() > 0){
					int len = fin.read(b);
					outStream.write(b, 0, len);
					outStream.flush();
				}
			} else if((file != null) && !file.isDirectory() && !file.exists() && file.getAbsolutePath().contains("profile")) {
				file = new File(ServerConfig.DIR_DATA_REPO, ctxCode + "/apu/image/user/profile/0.png");
				fin = new FileInputStream(file);
				byte b[] = new byte[1024];
				while(fin.available() > 0){
					int len = fin.read(b);
					outStream.write(b, 0, len);
					outStream.flush();
				}
			}
			else if((file != null) && !file.isDirectory() && !file.exists() && file.getAbsolutePath().contains("profile")) {
				file = new File(ServerConfig.DIR_DATA_REPO, ctxCode + "/apu/image/user/profile/0.jpg");
				fin = new FileInputStream(file);
				byte b[] = new byte[1024];
				while(fin.available() > 0){
					int len = fin.read(b);
					outStream.write(b, 0, len);
					outStream.flush();
				}
			}else {
				throw new IOException("Not valid file " + request.getRequestURI());
			}
		} catch(Exception e){
			logger.error(e.getMessage());
		} finally {
			if(outStream != null){
				outStream.flush();
				outStream.close();
			}
			if(fin != null) {
				fin.close();
			}
		}
	}
}
