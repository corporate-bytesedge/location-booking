package com.bytesedge.bookvenue.controller.organization;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.model.Organization;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class OrganizationXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<Organization> list = (List<Organization>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("OrganizationList");
				

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("PhoneNumber");
				header.createCell(2).setCellValue("MobileNumber");
				header.createCell(3).setCellValue("EmailId");
				
				// Create data cells
				int rowCount = 1;
				for (Organization organization  : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(organization .getName());
					courseRow.createCell(1).setCellValue(organization .getPhoneNumber());
					courseRow.createCell(2).setCellValue(organization .getMobileNumber());
					courseRow.createCell(3).setCellValue(organization .getEmailId());
				}					
			}
		}
	}
}