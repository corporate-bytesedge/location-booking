package com.bytesedge.bookvenue.common;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	private static volatile EmailService inst = null;
	private static volatile VelocityEngine velocityEngine = null;
	private static String velocityTemplatePathprefix = System.getProperty("server.home") + "/webapps/ROOT/WEB-INF/tmpl/email/";
	//private static String velocityTemplatePathprefix = "WEB-INF/view/email/";
	
	public static EmailService getInstance() {
		if(inst != null) {
			return inst;
		} else {
			inst = new EmailService();
			velocityEngine = new VelocityEngine();
			Properties p = new Properties();
			p.put("file.resource.loader.path", velocityTemplatePathprefix);
            p.setProperty(RuntimeConstants.INPUT_ENCODING,"UTF-8");
			p.setProperty(RuntimeConstants.OUTPUT_ENCODING,"UTF-8");
			p.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
			p.setProperty("runtime.log.logsystem.log4j.logger", EmailService.class.getName());
	        velocityEngine.init(p);
			return inst;
		}
	}
	
	public void sendEmail(String to, String emailTemplate, String subject, Map<String, Object> attr) throws Exception {
		final String smtpUsername = "beshilpa19@gmail.com";
		final String smtpPassword = "Creative1@Q";
		final String smtpServer = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, null);
		try {
			Template t = velocityEngine.getTemplate(emailTemplate);
		     
			VelocityContext context = new VelocityContext();
			if(attr != null) {
				for(String key : attr.keySet()) {
					context.put(key, attr.get(key));
				}
			}
			     
			StringWriter writer = new StringWriter();
			t.merge( context, writer );
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("beshilpa19@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(writer.toString(), "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, smtpUsername, smtpPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			logger.error("Failed to send email for this event To=" + to + ", Subject=" + subject, e);
		}
	}
	
	public static void sendEmail(String to, String subject, String body) throws Exception {
		final String smtpUsername = "beshilpa19@gmail.com";
		final String smtpPassword = "Creative1@Q";
		final String smtpServer = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//Session session = Session.getDefaultInstance(props, null);
		try {
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(smtpUsername, smtpPassword);
						}
					  });
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(smtpUsername));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);
	  		/**
			Transport transport = session.getTransport("smtp");
			transport.connect(smtpServer, smtpUsername, smtpPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			**/
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		try {
			EmailService.sendEmail("beshilpa19@gmail.com", "Test email " + System.currentTimeMillis(), "Test email 01 body");
			System.out.println("Sent email successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}