package com.bytesedge.bookvenue.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

public class XlsxStreamingView extends AbstractXlsxStreamingView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) model.get("map");
		if(map != null) {
			String fileName = (String) map.get("fileName");
			if(fileName != null) {
				fileName = fileName.replaceAll(" ", "-");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");			
			} else {
				throw new Exception("Invalid fileName=" + fileName);
			}
			
			String viewClass = (String) map.get("viewClass");
			if(viewClass != null) {
				XlsxStreamingViewIntf xlxs = (XlsxStreamingViewIntf) Class.forName(viewClass).newInstance();
				xlxs.createWorkBookData(model, workbook, request, response);
			} else {
				throw new Exception("Invalid viewClass=" + viewClass);
			}
		} else {
			throw new Exception("Invalid Params");
		}
	}
}