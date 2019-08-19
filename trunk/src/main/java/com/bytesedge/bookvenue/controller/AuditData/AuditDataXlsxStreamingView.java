package com.bytesedge.bookvenue.controller.AuditData;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.model.AuditData;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;


public class AuditDataXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		if(map != null) {
			List<AuditData> list = (List<AuditData>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("AuditDataList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Operation");
				header.createCell(1).setCellValue("AuditObject");
				header.createCell(2).setCellValue("Source");
				header.createCell(3).setCellValue("Destination");
				header.createCell(4).setCellValue("CreatedTime");
				header.createCell(5).setCellValue("CreatedUserName");
				
				
				// Create data cells
				int rowCount = 1;
				for (AuditData auditData : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(auditData.getOperation().name());
					courseRow.createCell(1).setCellValue(auditData.getAuditObject().name());
					courseRow.createCell(2).setCellValue(auditData.getSrc());
					courseRow.createCell(3).setCellValue(auditData.getDst());
					if(auditData.getCreatedTime() != null) {
						courseRow.createCell(4).setCellValue(format.format(auditData.getCreatedTime()));
					}
					courseRow.createCell(5).setCellValue(auditData.getCreatedUserName());
					
				}					
			}
		}
	}
}