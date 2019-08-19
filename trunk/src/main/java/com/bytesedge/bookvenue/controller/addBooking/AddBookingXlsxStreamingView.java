package com.bytesedge.bookvenue.controller.addBooking;

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
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class AddBookingXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<EndUser> list = (List<EndUser>) map.get("list");
			if(list != null) {
				for (EndUser addBooking : list) {
					try {
						addBooking.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), addBooking.getOrgId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						addBooking.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), addBooking.getPropertyId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						addBooking.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), addBooking.getPurposeId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						addBooking.setCreatedUserName(CacheService
								.getUserById(ControllerUtil.getContextId(request), addBooking.getCreatedUserId()).getDisplayName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("PropertyList");

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
				header.createCell(10).setCellValue("Booked by");
				// Create data cells
				int rowCount = 1;
				for (EndUser addBooking : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(addBooking.getApplicationId());
					courseRow.createCell(1).setCellValue(addBooking.getName());
					courseRow.createCell(2).setCellValue(addBooking.getEmail());
					courseRow.createCell(3).setCellValue(addBooking.getIdProofType().name());
					courseRow.createCell(4).setCellValue(addBooking.getIdProof());
					courseRow.createCell(5).setCellValue(addBooking.getMobileNumber());
					String pattern = "dd-MMM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					courseRow.createCell(6).setCellValue(simpleDateFormat.format(addBooking.getBookingDate()));
					courseRow.createCell(7).setCellValue(addBooking.getAddr());
					courseRow.createCell(8).setCellValue(addBooking.getPurposeName());
					courseRow.createCell(9).setCellValue(addBooking.getPropertyName());
					courseRow.createCell(10).setCellValue(addBooking.getCreatedUserName());
				}					
			}
		}
	}
}