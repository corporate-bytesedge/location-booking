package com.bytesedge.bookvenue.controller.RentPurpose;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.bytesedge.bookvenue.model.RentPurpose;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class RentPurposeXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if (map != null) {
			List<RentPurpose> list = (List<RentPurpose>) map.get("list");
			if (list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("RentPurposeList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("State");
				header.createCell(2).setCellValue("Description");

				// Create data cells
				int rowCount = 1;
				for (RentPurpose rentPurpose : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(rentPurpose.getName());
					courseRow.createCell(1).setCellValue(rentPurpose.getState().name());
					courseRow.createCell(2).setCellValue(rentPurpose.getDescr());
				}
			}
		}
	}
}