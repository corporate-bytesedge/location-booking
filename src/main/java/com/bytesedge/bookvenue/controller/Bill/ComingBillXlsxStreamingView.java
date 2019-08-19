package com.bytesedge.bookvenue.controller.Bill;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.bytesedge.bookvenue.common.CacheService;
import com.bytesedge.bookvenue.common.DbService;
import com.bytesedge.bookvenue.controller.ControllerUtil;
import com.bytesedge.bookvenue.model.EndUser;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;


public class ComingBillXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<EndUser> list = DbService.getInstance().getSetupService().getEndUserComingBookingList();
			if(list != null) {
					for (EndUser bill : list) {
						try {
							bill.setOrgName(CacheService
									.getOrganizationById(ControllerUtil.getContextId(request), bill.getOrgId()).getName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							bill.setPropertyName(CacheService
									.getPropertyById(ControllerUtil.getContextId(request), bill.getPropertyId()).getName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							bill.setPurposeName(CacheService
									.getPurposeById(ControllerUtil.getContextId(request), bill.getPurposeId()).getName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("BillList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Application Id");
				header.createCell(1).setCellValue("Name");
				header.createCell(2).setCellValue("Mobile number");
				header.createCell(3).setCellValue("IdProof Type");
				header.createCell(4).setCellValue("Event Date");
				header.createCell(5).setCellValue("Venue");
				header.createCell(6).setCellValue("Purpose");
				header.createCell(7).setCellValue("Price");
				/*header.createCell(8).setCellValue("CGST");
				header.createCell(9).setCellValue("SGST");*/
				header.createCell(10).setCellValue("Total");
				header.createCell(12).setCellValue("Payment status");
				header.createCell(13).setCellValue("Booking Date");

				// Create data cells
				int rowCount = 1;
				for (EndUser bill : list) {
					Row billRow = sheet.createRow(rowCount++);
					billRow.createCell(0).setCellValue(bill.getApplicationId());
					billRow.createCell(1).setCellValue(bill.getName());
					billRow.createCell(2).setCellValue(bill.getMobileNumber());
					billRow.createCell(3).setCellValue(bill.getIdProofType().name());
					String pattern = "dd-MMM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					billRow.createCell(4).setCellValue(simpleDateFormat.format(bill.getBookingDate()));
					billRow.createCell(5).setCellValue(bill.getPropertyName());
					billRow.createCell(6).setCellValue(bill.getPurposeName());
					billRow.createCell(7).setCellValue(bill.getPrice());
					billRow.createCell(8).setCellValue(bill.getCgst());
					billRow.createCell(9).setCellValue(bill.getSgst());
					billRow.createCell(10).setCellValue(bill.getTotal());
					billRow.createCell(12).setCellValue(bill.getPaymentStatus().toString());
					String pattern1 = "dd-MMM-yyyy hh:mm:ss";
					SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
					billRow.createCell(13).setCellValue(simpleDateFormat1.format(bill.getCreatedTime()));
				}					
			}
		}
	}
