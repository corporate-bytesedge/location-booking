package com.bytesedge.bookvenue.controller.Invoice;
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
import com.bytesedge.bookvenue.model.Invoice;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class InvoicePaidXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<EndUser> list = DbService.getInstance().getSetupService().getInvoicePaidList();
			if(list != null) {
				
				for (EndUser invoice : list) {
					try {
						invoice.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), invoice.getOrgId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						invoice.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), invoice.getPropertyId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						invoice.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), invoice.getPurposeId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("InvoiceList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Venue");
				header.createCell(1).setCellValue("Purpose");
				header.createCell(2).setCellValue("Status");
				header.createCell(3).setCellValue("Total Price");
				header.createCell(4).setCellValue("Event Date");
				header.createCell(5).setCellValue("Rent Price");
				
				// Create data cells
				int rowCount = 1;
				for (EndUser invoice : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(invoice.getPropertyName());
					courseRow.createCell(1).setCellValue(invoice.getPurposeName());
					courseRow.createCell(2).setCellValue(invoice.getPaymentStatus().toString());
					courseRow.createCell(3).setCellValue(invoice.getTotal());
					courseRow.createCell(4).setCellValue(invoice.getBookingDate());
					courseRow.createCell(5).setCellValue(invoice.getPrice());
					
				}					
			}
		}
	}
}