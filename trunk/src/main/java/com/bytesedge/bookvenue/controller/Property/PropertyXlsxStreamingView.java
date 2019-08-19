package com.bytesedge.bookvenue.controller.Property;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.bytesedge.bookvenue.model.Property;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class PropertyXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if(map != null) {
			List<Property> list = (List<Property>) map.get("list");
			if(list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("PropertyList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("State");
				header.createCell(2).setCellValue("Description");
				
				// Create data cells
				int rowCount = 1;
				for (Property property : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(property.getName());
					courseRow.createCell(1).setCellValue(property.getState().name());
					courseRow.createCell(2).setCellValue(property.getDescr());
				}					
			}
		}
	}
}