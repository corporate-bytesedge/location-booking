package com.bytesedge.bookvenue.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

// http://api.msg91.com/api/sendhttp.php?country=91&sender=TESTIN&route=4&mobiles=+918019016669&authkey=255358AOnWLXv2pr5c32fb5b&message=OTP - Please use the OTP BKI-234567 to register to Shilparamam Venbue Booking Application. This OTP will expire in 10 minutes.
public class SmsServiceMsg91 implements SmsService {
	// Replace with your username
		private static final String country = "91";
		private static final String sender = "SHILPA";
		private static final String route = "4";
		

		// Replace with your API KEY (We have sent API KEY on activation email, also
		// available on panel)
		private static final String apikey = "255358AOnWLXv2pr5c32fb5b";

		public void sendSms(String mobile, String message) throws Exception {
			// Prepare Url
			URLConnection myURLConnection = null;
			URL myURL = null;
			BufferedReader reader = null;

			// encoding message
			String encoded_message = URLEncoder.encode(message);

			// Send SMS API
			String mainUrl = "http://api.msg91.com/api/sendhttp.php?";

			// Prepare parameter string
			StringBuilder sbPostData = new StringBuilder(mainUrl);
			sbPostData.append("&country=" + country);
			sbPostData.append("&sender=" + sender);
			sbPostData.append("&route=" + route);
			sbPostData.append("&mobiles=" + mobile);
			sbPostData.append("&authkey=" + apikey);
			sbPostData.append("&message=" + encoded_message);

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
				SmsServiceFactory.getInstance().getService().sendSms("+919988776655", "OTP testing ");
				System.out.println("SMS email successfully");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}