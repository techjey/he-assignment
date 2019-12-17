package com.jithin.test.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, ByteArrayResource byteArrResource) {
		
		if(byteArrResource == null) {
			throw new IllegalArgumentException("File source can not be empty");
		}
		
		try {
			MimeMessage message = emailSender.createMimeMessage();
			// pass 'true' to the constructor to create a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			helper.addAttachment("Report.csv", byteArrResource);
			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
