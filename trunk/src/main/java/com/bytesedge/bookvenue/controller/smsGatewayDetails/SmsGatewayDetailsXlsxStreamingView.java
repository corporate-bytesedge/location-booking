package com.bytesedge.bookvenue.controller.smsGatewayDetails;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.bytesedge.bookvenue.model.SmsGatewayDetails;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class SmsGatewayDetailsXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if (map != null) {
			List<SmsGatewayDetails> list = (List<SmsGatewayDetails>) map.get("list");
			if (list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("SmsGatewayDetailsList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("senderId");
				header.createCell(1).setCellValue("Route");

				// Create data cells
				int rowCount = 1;
				for (SmsGatewayDetails smsGatewayDetails : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(smsGatewayDetails.getSenderId());
					courseRow.createCell(1).setCellValue(smsGatewayDetails.getRoute());
				}
			}
		}
	}
}