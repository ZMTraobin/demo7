package com.example.demo.servicesimpl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.services.MailService;

@Component
public class MailServiceImpl implements MailService {

	private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.fromMail.addr}")
	private String fromAddr;

	/**
	 * 发送普通文本的内容
	 */
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddr);
		message.setTo(to);
		message.setText(content);
		message.setSubject(subject);
		try {
			javaMailSender.send(message);
			log.info("邮件发送成功");
		} catch (Exception e) {
			log.error("邮件发送出现异常" + e);
		}
	}

	/**
	 * 发送html内容
	 */
	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromAddr);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			javaMailSender.send(message);
			log.info("发送html格式的邮件成功");
		} catch (MessagingException e) {
			log.error("发送邮件过程出现错误：" + e);
		}
	}

	/**
	 * 发送附件邮件
	 */
	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		// TODO Auto-generated method stub
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			//发送信息
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setFrom(fromAddr);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
			//添加附件
			FileSystemResource resource = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			//添加多个附件可以使用多条 helper.addAttachment(fileName, file)
			helper.addAttachment(fileName, resource);
			
			javaMailSender.send(message);
			log.info("发送附件邮件成功");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.info("发送附件邮件出现异常："+e);
		}
		
	}

	/**
	 * 发送附件为音频，图片，视频的邮件
	 */
	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		// TODO Auto-generated method stub
		MimeMessage message = javaMailSender.createMimeMessage();
		//发送信息
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
			helper.setFrom(fromAddr);
			//静态资源（例如：图片，视频，音频）
			FileSystemResource resource = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, resource);
			//发送
			javaMailSender.send(message);
			log.info("邮件发送成");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			log.error("邮件发送出现错误："+e);
		}
		
		
	}

}
