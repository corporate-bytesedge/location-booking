package com.bytesedge.bookvenue.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

public interface XlsxStreamingViewIntf {
	public void createWorkBookData(Map<String, Object> model, Workbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}