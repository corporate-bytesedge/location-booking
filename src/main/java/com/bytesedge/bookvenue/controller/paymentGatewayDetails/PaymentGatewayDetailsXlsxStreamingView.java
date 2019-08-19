package com.bytesedge.bookvenue.controller.paymentGatewayDetails;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.bytesedge.bookvenue.model.PaymentGatewayDetails;
import com.bytesedge.bookvenue.view.XlsxStreamingViewIntf;

public class PaymentGatewayDetailsXlsxStreamingView implements XlsxStreamingViewIntf {

	@SuppressWarnings("unchecked")
	@Override
	public void createWorkBookData(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if (map != null) {
			List<PaymentGatewayDetails> list = (List<PaymentGatewayDetails>) map.get("list");
			if (list != null) {
				// create excel xls sheet
				Sheet sheet = workbook.createSheet("PaymentGatewayDetailsList");

				// create header row
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Login");
				header.createCell(1).setCellValue("Password");
				header.createCell(2).setCellValue("Request hash key");
				header.createCell(3).setCellValue("Response hash key");
				header.createCell(4).setCellValue("Status");
				header.createCell(5).setCellValue("Currency");
				header.createCell(6).setCellValue("Transaction type");
				header.createCell(7).setCellValue("Product id");
				header.createCell(8).setCellValue("Url");

				// Create data cells
				int rowCount = 1;
				for (PaymentGatewayDetails paymentGatewayDetails : list) {
					Row courseRow = sheet.createRow(rowCount++);
					courseRow.createCell(0).setCellValue(paymentGatewayDetails.getLogin());
					courseRow.createCell(1).setCellValue(paymentGatewayDetails.getPassword());
					courseRow.createCell(2).setCellValue(paymentGatewayDetails.getReqHashKey());
					courseRow.createCell(3).setCellValue(paymentGatewayDetails.getResHashKey());
					courseRow.createCell(4).setCellValue(paymentGatewayDetails.getStatus().name());
					courseRow.createCell(5).setCellValue(paymentGatewayDetails.getTxnCurr());
					courseRow.createCell(6).setCellValue(paymentGatewayDetails.getTtype());
					courseRow.createCell(7).setCellValue(paymentGatewayDetails.getProdId());
					courseRow.createCell(8).setCellValue(paymentGatewayDetails.getMerchantUrl());
				}
			}
		}
	}
}