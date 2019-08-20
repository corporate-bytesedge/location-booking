package com.bytesedge.bookvenue.pg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.bytesedge.bookvenue.util.UUIDUtil;

public class PaymentGatewaySignatureGenerateAtom {

	try
	{
		
	URL url = new URL ("https://test.sbiepay.com/payagg/orderStatusQuery/getOrderStatusQuery");
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	HashMap<String, String> params = new HashMap<String,String>();
	params.put("queryRequest","queryRequest");
	params.put("aggregatorId","aggregatorId");
	params.put("merchantId"," merchantId");
	if (params != null&& params.size() > 0) {
	conn.setDoOutput(true); // true indicates POST request
	// creates the params string, encode them using URLEncoder
	Iterator<String> paramIterator = params.keySet().iterator();
	while (paramIterator.hasNext())
	{
	String key = paramIterator.next();
	String value = params.get(key);
	requestParams.append(URLEncoder.encode(key, "UTF-8"));
	requestParams.append(URLEncoder.encode(value, "UTF-8"));
	}
	}
	conn.setReadTimeout(10000);
	conn.setConnectTimeout(15000);
	conn.setRequestMethod("POST");
	conn.setDoInput(true);
	conn.setDoOutput(true);
	conn.setRequestProperty("Accept-Language" , "en-US,en;q=0.5");
	conn.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	wr.writeBytes(requestParams.toString());
	wr.flush();
	wr.close();
	//Response Code
	int responseCode = conn.getResponseCode();
	//Reading Response
	InputStream stream = conn.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	StringBuilder sb = new StringBuilder();
	String line = null ;
	while((line = reader.readLine()) != null) {
	sb.append(line).append("\n");
	}
	stream.close();
	responseCode = sb.toString() ;
	responseCode = responseCode.trim();
	System.out.println("responseCode:"+responseCode);
	}catch (MalformedURLException e)
	{e.printStackTrace();
	} 
	catch (ProtocolException e1)
	{
		e.printStackTrace();}
	catch(
	IOException t)
	{
		e.printStackTrace();
	}
	}
}
}
