package com.miniproj.blog.emailService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private Configuration freemakeconfig;
		
	public void sendWelcomeMessage(String to,String name) throws IOException, TemplateException, MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("name",name);
		freemakeconfig.setClassForTemplateLoading(this.getClass(),"/templates/MailTemplate");
		Template t = freemakeconfig.getTemplate("welcome.ftl");
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		helper.setFrom("pranavadurai@gmail.com");
		helper.setTo(to);
		helper.setSubject("Welcome to Pranav's Blog Application");
		helper.setText(text,true);
		emailSender.send(message);
	}
	
}
