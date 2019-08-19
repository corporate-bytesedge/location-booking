package com.bytesedge.bookvenue.controller.userRole;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.model.UserRole;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;


public class UserRoleXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<UserRole> list = (List<UserRole>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("UserRoleList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("Admin");
				
				// Create data cells
				int rowCount = 1;
				for (UserRole userRole : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(userRole.getName());
					courseRow.createCell(1).setCellValue(userRole.getAdmin());
				}					
			}
		}
	}
}