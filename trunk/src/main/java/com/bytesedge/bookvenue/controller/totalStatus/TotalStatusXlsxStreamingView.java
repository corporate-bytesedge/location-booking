package com.bytesedge.bookvenue.controller.totalStatus;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.model.TotalStatus;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class TotalStatusXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<EndUser> list = (List<EndUser>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("PropertyList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("Email id");
				header.createCell(2).setCellValue("Mobile number");
				header.createCell(3).setCellValue("Venue Date");
				header.createCell(4).setCellValue("venue");
				header.createCell(5).setCellValue("Purpose");
				header.createCell(6).setCellValue("Referred By");
				// Create data cells
				int rowCount = 1;
				for (EndUser totalStatus : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(totalStatus.getName());
					courseRow.createCell(1).setCellValue(totalStatus.getEmail());
					courseRow.createCell(2).setCellValue(totalStatus.getMobileNumber());
					courseRow.createCell(3).setCellValue(totalStatus.getBookingDate());
					courseRow.createCell(4).setCellValue(totalStatus.getPropertyName());
					courseRow.createCell(5).setCellValue(totalStatus.getPurposeName());
					courseRow.createCell(6).setCellValue(totalStatus.getReferredBy());
				}					
			}
		}
	}
}