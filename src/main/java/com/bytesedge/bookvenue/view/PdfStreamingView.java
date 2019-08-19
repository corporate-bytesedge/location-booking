package com.bytesedge.bookvenue.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PdfStreamingView extends AbstractPdfStamperViewImpl implements MessageSourceAware {

	private MessageSource messageSource;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// IE workaround: write into byte array first.
		ByteArrayOutputStream baos = createTemporaryOutputStream();

		PdfReader reader = readPdfResource(model);
		PdfStamper stamper = new PdfStamper(reader, baos);
		mergePdfDocument(model, stamper, request, response);
		stamper.close();

		// Flush to HTTP response.
		writeToResponse(response, baos);
	}
	
	@SuppressWarnings("unchecked")
	protected PdfReader readPdfResource(Map<String, Object> model) throws IOException {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			String tmplName = (String) map.get("tmplName");
			if(tmplName != null) {
				return new PdfReader(getApplicationContext().getResource(tmplName).getInputStream());
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
    protected void mergePdfDocument(Map<String, Object> model,
            PdfStamper stamper, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		try {
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
					PdfStreamingViewIntf pdf = (PdfStreamingViewIntf) Class.forName(viewClass).newInstance();
					pdf.mergePdfDocument(model, stamper, request, response);
				} else {
					throw new Exception("Invalid viewClass=" + viewClass);
				}
			} else {
				throw new Exception("Invalid Params");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			stamper.close();
		}
    }
}