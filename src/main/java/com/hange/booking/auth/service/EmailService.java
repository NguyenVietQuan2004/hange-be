//package com.hange.booking.auth.service;
//
//import java.nio.charset.StandardCharsets;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//	private final JavaMailSender javaMailSender;
//	@Value("${app.frontend.base-url}")
//	private String frontendBaseUrl;
//	private final SpringTemplateEngine templateEngine;
//
//	public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
//		// Prepare message using a Spring helper
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//		try {
//			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
//			message.setTo(to);
//			message.setSubject(subject);
//			message.setText(content, isHtml);
//
//			javaMailSender.send(mimeMessage);
//			System.out.println("Email sent successfully to: " + to);
//		} catch (Exception e) {
//			System.out.println("ERROR SEND EMAIL: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	@Async
//	public void sendEmailVerifyAsync(String email, String subject, String templateName, String rawToken) {
//		Context context = new Context();
//		context.setVariable("email", email);
//		context.setVariable("verifyUrl", frontendBaseUrl + "/verify?token=" + rawToken);
//		String content = templateEngine.process("email/verify-email", context);
//
//		this.sendEmailSync(email, subject, content, false, true);
//	}
//}

package com.hange.booking.auth.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	private final SpringTemplateEngine templateEngine;

	@Value("${app.frontend.base-url}")
	private String frontendBaseUrl;

	public void sendEmail(String to, String subject, String content, boolean isHtml) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, isHtml);

			javaMailSender.send(mimeMessage);

		} catch (Exception e) {
			throw new RuntimeException("Failed to send email", e);
		}
	}

	@Async
	public void sendTemplateEmail(String to, String subject, String templateName, Context context) {
		String content = templateEngine.process(templateName, context);
		sendEmail(to, subject, content, true);
	}

	@Async
	public void sendVerifyEmail(String email, String rawToken) {

		Context context = new Context();
		context.setVariable("verifyUrl", frontendBaseUrl + "/verify?token=" + rawToken);
		context.setVariable("email", email);
		sendTemplateEmail(email, "Verify your account", "email/verify-email", context);
	}

	@Async
	public void sendForgotPasswordEmail(String email, String rawToken) {

		Context context = new Context();
		context.setVariable("resetUrl", frontendBaseUrl + "/reset-password?token=" + rawToken);
		context.setVariable("email", email);
		sendTemplateEmail(email, "Reset your password", "email/forgot-password", context);
	}
}