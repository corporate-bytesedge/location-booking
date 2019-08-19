package com.bytesedge.bookvenue.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsServiceHorizon implements SmsService {
	// Replace with your username
	private static final String user = "pcvarma";

	// Replace with your API KEY (We have sent API KEY on activation email, also
	// available on panel)
	private static final String apikey = "123";

	// For Plain Text, use "txt" ; for Unicode symbols or regional Languages
	// like hindi/tamil/kannada use "uni"
	private static final String type = "txt";

	// Replace if you have your own Sender ID, else donot change
	private static final String senderid = "MYTEXT";

	public void sendSms(String mobile, String message) throws Exception {
		// Prepare Url
		URLConnection myURLConnection = null;
		URL myURL = null;
		BufferedReader reader = null;

		// encoding message
		String encoded_message = URLEncoder.encode(message);

		// Send SMS API
		String mainUrl = "http://smshorizon.co.in/api/sendsms.php?";

		// Prepare parameter string
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		sbPostData.append("user=" + user);
		sbPostData.append("&apikey=" + apikey);
		sbPostData.append("&message=" + encoded_message);
		sbPostData.append("&mobile=" + mobile);
		sbPostData.append("&senderid=" + senderid);
		sbPostData.append("&type=" + type);

		// final string
		mainUrl = sbPostData.toString();
		try {
			// prepare connection
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			// reading response
			String response;
			while ((response = reader.readLine()) != null)
				// print response
				System.out.println(response);

			// finally close connection
			reader.close();
		} catch (IOException e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			SmsServiceFactory.getInstance().getService().sendSms("8019016669", "OTP 6789 ");
			System.out.println("SMS email successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}