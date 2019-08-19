package com.bytesedge.bookvenue.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.pdf.PdfStamper;

public interface PdfStreamingViewIntf {
	public void mergePdfDocument(Map<String, Object> model,
            PdfStamper stamper, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
	
	
}