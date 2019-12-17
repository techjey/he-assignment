package com.jithin.test.mail;

import org.springframework.core.io.ByteArrayResource;

public interface EmailService {

	void sendMessageWithAttachment(String to, String subject, String text, ByteArrayResource inputStream);

}
