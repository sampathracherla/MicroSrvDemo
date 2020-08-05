package com.msdemo.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.msdemo.model.User;

@Service
public class MSMailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendMail(User user, String url) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable("url", url);
		String html = templateEngine.process("register", context);
		helper.setTo(user.getEmail());
		helper.setFrom("test@email.com");
		helper.setSubject("Registration Details");
		helper.setText(html, true);
		mailSender.send(mimeMessage);
	}
}
