package com.bytesedge.bookvenue.controller.EndUser;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;


public class EndUserXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<EndUser> list = (List<EndUser>) map.get("list");
			if(list != null) {
				
				for (EndUser endUser : list) {
					try {
						endUser.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), endUser.getOrgId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						endUser.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), endUser.getPropertyId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						endUser.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), endUser.getPurposeId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("EndUsersList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Application Id");
				header.createCell(1).setCellValue("Name");
				header.createCell(2).setCellValue("Email");
				header.createCell(3).setCellValue("Id Proof Type");
				header.createCell(4).setCellValue("Id Proof");
				header.createCell(5).setCellValue("Mobile number");
				header.createCell(6).setCellValue("Event Date");
				header.createCell(7).setCellValue("Address");
				header.createCell(8).setCellValue("Purpose");
				header.createCell(9).setCellValue("Venue");
				
				// Create data cells
				int rowCount = 1;
				for (EndUser endUser : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(endUser.getApplicationId());
					courseRow.createCell(1).setCellValue(endUser.getName());
					courseRow.createCell(2).setCellValue(endUser.getEmail());
					courseRow.createCell(3).setCellValue(endUser.getIdProofType().name());
					courseRow.createCell(4).setCellValue(endUser.getIdProof());
					courseRow.createCell(5).setCellValue(endUser.getMobileNumber());
					String pattern = "dd-MMM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					courseRow.createCell(6).setCellValue(simpleDateFormat.format(endUser.getBookingDate()));
					courseRow.createCell(7).setCellValue(endUser.getAddr());
					courseRow.createCell(8).setCellValue(endUser.getPurposeName());
					courseRow.createCell(9).setCellValue(endUser.getPropertyName());
				}					
			}
		}
	}
}