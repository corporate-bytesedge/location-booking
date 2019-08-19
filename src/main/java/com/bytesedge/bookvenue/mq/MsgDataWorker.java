package com.bytesedge.bookvenue.mq;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgDataWorker implements Runnable {
	private static Logger log = LoggerFactory.getLogger(MsgDataWorker.class);
	private String id = null;
	private String msg = null;
	
	private ObjectMapper jsonApi = null;

	public MsgDataWorker(final String id, final String msg) {
		this.id = id;
		this.msg = msg;
		
		jsonApi = new ObjectMapper();
		jsonApi.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public void run() {
		if(this.msg == null) {
			log.error("Received empty data");
			return;
		}
		
		log.error("Processing msg uuid = " + this.id);
		
		// Save as JSON file
		try {
			String fileName = "/tmp/msg-" + id + ".json";
			File file = new File(fileName);
			FileOutputStream fout = new FileOutputStream(file);
			fout.write((this.msg).getBytes());
			fout.flush();
			fout.close();
			log.error("Msg has been saved to " + fileName);
		} catch (Exception e) {
			// ignore
		}
		
		try {
			// Step 01: Update the DB with new JSON payload
			log.error("Processing Message....\n" + this.msg);
			// Done with processing the Mon configuration
		} catch (Exception e) {
			log.error("Not able to process msg" + this.msg, e);
		}
	}
}