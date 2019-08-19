package com.bytesedge.bookvenue.pg;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import com.bytesedge.bookvenue.util.UUIDUtil;

public class PaymentGatewaySignatureGenerateAtom {
	public static byte[] encodeWithHMACSHA2(String text, String keyString)
			throws java.security.NoSuchAlgorithmException, java.security.InvalidKeyException,
			java.io.UnsupportedEncodingException {

		java.security.Key sk = new javax.crypto.spec.SecretKeySpec(keyString.getBytes("UTF-8"), "HMACSHA512");
		javax.crypto.Mac mac = javax.crypto.Mac.getInstance(sk.getAlgorithm());
		mac.init(sk);

		byte[] hmac = mac.doFinal(text.getBytes("UTF-8"));

		return hmac;
	}

	public static String byteToHexString(byte byData[]) {
		StringBuilder sb = new StringBuilder(byData.length * 2);

		for (int i = 0; i < byData.length; i++) {
			int v = byData[i] & 0xff;
			if (v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
		}

		return sb.toString();
	}

	/*
	 * Encoded with HMACSHA512 and encoded with utf-8 using url encoder for
	 * given list of parameter values appended with the key
	 */
	public static String getEncodedValueWithSha2(String hashKey, String... param) {
		String resp = null;

		StringBuilder sb = new StringBuilder();
		for (String s : param) {
			sb.append(s);
		}

		try {
			System.out.println("[getEncodedValueWithSha2]String to Encode =" + sb.toString());
			resp = byteToHexString(encodeWithHMACSHA2(sb.toString(), hashKey));
			// resp = URLEncoder.encode(resp,"UTF-8");

		} catch (Exception e) {
			System.out.println("[getEncodedValueWithSha2]Unable to encocd value with key :" + hashKey + " and input :"
					+ sb.toString());
			e.printStackTrace();
		}

		return resp;
	}
	public static String getPaymentGatewayResSig(String resHashKey, String mmp_txn, String mer_txn, String f_code, 
			String prod, String discriminator, String amt, String bank_txn) {
			return getEncodedValueWithSha2(resHashKey, mmp_txn, mer_txn, f_code, prod, discriminator, amt, bank_txn);
	}
	
	public static PaymentGatewayReq getPaymentGatewayReq(String reqHashKey, String login, String pass, String ttype, String prodid, Float amt
			, String txncurr, Float txnscamt, String clientcode, String curTxId, Date curDate
			, String custName, String returnUrl, String custEmail, String custMobile) {
		try {
			PaymentGatewayReq req = new PaymentGatewayReq();
			
			// login,pass,ttype,prodid,txnid,amt,txncurr
			String signature_request = getEncodedValueWithSha2(reqHashKey, login, pass, ttype, prodid, curTxId, String.format("%.02f", amt), txncurr);
			System.out.println("Request haskey" +reqHashKey);
			System.out.println("Login" +login);
			System.out.println("Password" +pass);
			System.out.println("Ttype" +ttype);
			System.out.println("Prodid" +prodid);
			System.out.println("CurTxid" +curTxId);
			System.out.println("Price" +String.format("%.02f", txnscamt));
			System.out.println("Txncurr" +txncurr);
			System.out.println("Request signature ::" + signature_request);
			req.setReqSign(signature_request);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String curDateStr = df.format(new Date());
			StringBuilder urlBuilder = new StringBuilder();
			//live payment link
			//urlBuilder.append("https://payment.atomtech.in/paynetz/epi/fts?");
			//test payment link
			urlBuilder.append("https://paynetzuat.atomtech.in/paynetz/epi/fts?");
			urlBuilder.append("login=").append(URLEncoder.encode( login, "UTF-8" ));
			urlBuilder.append("&pass=").append(URLEncoder.encode( pass, "UTF-8" ));
			urlBuilder.append("&ttype=").append(URLEncoder.encode(ttype, "UTF-8" ));
			urlBuilder.append("&prodid=").append(URLEncoder.encode( prodid, "UTF-8" ));
			urlBuilder.append("&amt=").append(URLEncoder.encode( String.format("%.02f", amt), "UTF-8" ));
			urlBuilder.append("&txncurr=").append(URLEncoder.encode( txncurr, "UTF-8" ));
			urlBuilder.append("&txnscamt=").append(URLEncoder.encode( String.format("%.02f", txnscamt), "UTF-8" ));
			urlBuilder.append("&clientcode=").append(Base64.getEncoder().encodeToString(clientcode.getBytes()));
			urlBuilder.append("&txnid=").append(URLEncoder.encode( curTxId, "UTF-8" ));
			urlBuilder.append("&date=").append(URLEncoder.encode( curDateStr, "UTF-8" ));
			urlBuilder.append("&custacc=").append(URLEncoder.encode( clientcode, "UTF-8" ));
			urlBuilder.append("&udf1=").append(URLEncoder.encode( custName, "UTF-8" ));
			urlBuilder.append("&udf9=").append(URLEncoder.encode( clientcode, "UTF-8" ));
			urlBuilder.append("&udf2=").append(URLEncoder.encode( custEmail, "UTF-8" ));
			urlBuilder.append("&udf3=").append(URLEncoder.encode( custMobile, "UTF-8" ));
			urlBuilder.append("&signature=").append(signature_request);
			urlBuilder.append("&ru=").append(URLEncoder.encode( returnUrl, "UTF-8" ));
			
			String url = urlBuilder.toString();
			System.out.println("URL=" + url);
			req.setPaymentUrl(url);
			return req;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String login = "197";
		String pass = "Test@123";
		String ttype = "NBFundTransfer";
		String prodid = "NSE";
		String txncurr = "INR";
		String reqHashKey = "KEY123657234";
		
		
		
		String curTxId = Long.toString(System.currentTimeMillis());
		String txnid = curTxId;
		String amt = "300000.00";

		
		String clientcode = UUIDUtil.getUuid(24);
		//txnid = clientcode;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String curDate = df.format(new Date());
		String custName = "user 0001";
		String custEmail = "divyajalli@gmail.com";
		String custMobile = "8019016669";
		
		// login,pass,ttype,prodid,txnid,amt,txncurr
		String signature_request = getEncodedValueWithSha2(reqHashKey, login, pass, ttype, prodid, txnid, amt, txncurr);
		System.out.println("111 Request signature ::" + signature_request);
		
		// Generate URL
		// https://paynetzuat.atomtech.in/paynetz/epi/fts?login=197&pass=Test@123&ttype=NBFundTransfer&prodid=NSE&amt=100.00&t txncurr=INR&txnscamt=0&clientcode=001&txnid=M123&date=23/08/2010%2011:57:00&custacc=100000036600&udf1= bobsmith &udf9=ABCD&ru=https://paynetzuat.atomtech.in/paynetzclient/ResponseParam.jsp&udf2=smith.bob@gmail.com&udf3=9999999 999&signature=704e6a78ca61c89127ca5430ddf59a329dacb142ae2790e19d676f5ca8ca80b9c534552e877bfb0ce1c2dddf252ae3d 7580d329556213811f828711e9f4ea371
		try {
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append("https://paynetzuat.atomtech.in/paynetz/epi/fts?");
			urlBuilder.append("login=").append(URLEncoder.encode( login, "UTF-8" ));
			urlBuilder.append("&pass=").append(URLEncoder.encode( pass, "UTF-8" ));
			urlBuilder.append("&ttype=").append(URLEncoder.encode(ttype, "UTF-8" ));
			urlBuilder.append("&prodid=").append(URLEncoder.encode( prodid, "UTF-8" ));
			urlBuilder.append("&amt=").append(URLEncoder.encode( amt, "UTF-8" ));
			urlBuilder.append("&txncurr=").append(URLEncoder.encode( txncurr, "UTF-8" ));
			urlBuilder.append("&txnscamt=").append(URLEncoder.encode( "3000.00", "UTF-8" ));
			urlBuilder.append("&clientcode=").append(Base64.getEncoder().encodeToString(clientcode.getBytes()));
			urlBuilder.append("&txnid=").append(URLEncoder.encode( curTxId, "UTF-8" ));
			urlBuilder.append("&date=").append(URLEncoder.encode( curDate, "UTF-8" ));
			urlBuilder.append("&custacc=").append(URLEncoder.encode( clientcode, "UTF-8" ));
			urlBuilder.append("&udf1=").append(URLEncoder.encode( custName, "UTF-8" ));
			urlBuilder.append("&udf9=").append(URLEncoder.encode( clientcode, "UTF-8" ));
			//urlBuilder.append("&ru=").append(URLEncoder.encode( "https://paynetzuat.atomtech.in/paynetzclient/ResponseParam.jsp", "UTF-8" ));
			urlBuilder.append("&ru=").append( "https://payment.bytesguru.com/apu/paymentGatewayResponse");
			urlBuilder.append("&udf2=").append(URLEncoder.encode( custEmail, "UTF-8" ));
			urlBuilder.append("&udf3=").append(URLEncoder.encode( custMobile, "UTF-8" ));
			urlBuilder.append("&signature=").append(signature_request);
			
			String url = urlBuilder.toString();
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Response signature based on parameters
		String mmp_txn = "121212";
		String mer_txn = "Mer123";
		String f_code = "Ok";
		String prod = "NSE";
		String discriminator = "NB";
		amt = "100.00";
		String bank_txn = "Bank123";
		String respHashKey = "KEYRESP123657234";
		// mmp_txn,mer_txn, f_code, prod, discriminator, amt, bank_txn
		String signature_response = getEncodedValueWithSha2(respHashKey, mmp_txn, mer_txn, f_code, prod, discriminator,
				amt, bank_txn);
		System.out.println("Response signature ::" + signature_response);
	}
}