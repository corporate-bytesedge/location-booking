package com.bytesedge.bookvenue.controller.PropertyRentPrice;
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
import com.bytesedge.bookvenue.model.PropertyRentPrice;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class PropertyRentPriceXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<PropertyRentPrice> list = (List<PropertyRentPrice>) map.get("list");
			if(list != null) {
				
				for (PropertyRentPrice propertyRentPrice : list) {
					try {
						propertyRentPrice.setOrgName(CacheService
								.getOrganizationById(ControllerUtil.getContextId(request), propertyRentPrice.getOrgId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						propertyRentPrice.setPropertyName(CacheService
								.getPropertyById(ControllerUtil.getContextId(request), propertyRentPrice.getPropertyId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						propertyRentPrice.setPurposeName(CacheService
								.getPurposeById(ControllerUtil.getContextId(request), propertyRentPrice.getPurposeId()).getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("PropertyRentPriceList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Slot type");
				//header.createCell(1).setCellValue("Start time");
				//header.createCell(2).setCellValue("End time");
				header.createCell(1).setCellValue("Price");
				header.createCell(2).setCellValue("Venue");
				header.createCell(3).setCellValue("purpose");
				header.createCell(4).setCellValue("state");
				header.createCell(5).setCellValue("CGST");
				header.createCell(6).setCellValue("SGST");
				
				
				
				// Create data cells
				int rowCount = 1;
				for (PropertyRentPrice propertyRentPrice : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(propertyRentPrice.getSlotType().name());
					//courseRow.createCell(1).setCellValue(propertyRentPrice.getStartTime());
					//courseRow.createCell(2).setCellValue(propertyRentPrice.getEndTime());
					courseRow.createCell(1).setCellValue(propertyRentPrice.getPrice());
					courseRow.createCell(2).setCellValue(propertyRentPrice.getPropertyName());
					courseRow.createCell(3).setCellValue(propertyRentPrice.getPurposeName());
					courseRow.createCell(4).setCellValue(propertyRentPrice.getState().name());
					courseRow.createCell(5).setCellValue(propertyRentPrice.getCgst());
					courseRow.createCell(6).setCellValue(propertyRentPrice.getSgst());
					
				}	

			}
		}
	}
}