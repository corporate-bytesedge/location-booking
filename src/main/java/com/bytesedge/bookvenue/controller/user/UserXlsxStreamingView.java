package com.bytesedge.bookvenue.controller.user;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.model.User;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;


public class UserXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<User> list = (List<User>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("UserList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("LoginName");
				header.createCell(1).setCellValue("AccountState");
				header.createCell(2).setCellValue("Email");
				header.createCell(3).setCellValue("DisplayName");
				header.createCell(4).setCellValue("Gender");
				header.createCell(5).setCellValue("AadharId");
				header.createCell(6).setCellValue("MobileNumber");
				header.createCell(7).setCellValue("Dob");
				header.createCell(8).setCellValue("Designation");
				header.createCell(9).setCellValue("RoleId");
				header.createCell(10).setCellValue("OrgId");
				
				// Create data cells
				int rowCount = 1;
				for (User user : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(user.getLoginName());
					courseRow.createCell(1).setCellValue(user.getAccountState().name());
					courseRow.createCell(2).setCellValue(user.getEmail());
					courseRow.createCell(3).setCellValue(user.getDisplayName());
					courseRow.createCell(4).setCellValue(user.getGender().name());
					courseRow.createCell(5).setCellValue(user.getAadharId());
					courseRow.createCell(6).setCellValue(user.getMobileNumber());
					
					String pattern = "dd-MMM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					courseRow.createCell(7).setCellValue(simpleDateFormat.format(user.getDob()));
					
					courseRow.createCell(8).setCellValue(user.getDesignation());
					courseRow.createCell(9).setCellValue(user.getRoleId());
					courseRow.createCell(10).setCellValue(user.getOrgId());
				}					
			}
		}
	}
}