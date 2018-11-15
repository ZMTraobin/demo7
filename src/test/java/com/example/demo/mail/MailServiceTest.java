package com.example.demo.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.services.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	

	@Test
	public void proccess() {
		System.out.println("发送邮件");
		//15810459893@163.com
		String to = "15810459893@163.com";
		String subject = "小狗，想我了没啊?";
		String content = "I Love ...You";
		mailService.sendSimpleMail(to, subject, content);
	}
	
	@Test
	public void proccess2() {
		System.out.println("发送邮件");
		//15810459893@163.com
		String to = "1900402045@qq.com";
		String subject = "小狗，想我了没啊?";
		String content = "<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
		mailService.sendSimpleMail(to, subject, content);
	}
	
	@Test
	public void proccess3() {
		System.out.println("发送邮件");
		//15810459893@163.com
		String to = "15311130895@163.com";
		String subject = "小狗，想我了没啊?";
		String content = "<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "</body>\n" +
	            "</html>";
		String filePath = "E:\\apache-tomcat-7.0.90\\BUILDING.txt";
		mailService.sendAttachmentsMail(to, subject, content, filePath);
	}
	@Test
	public void proccess4() {
		System.out.println("发送邮件");
		//15810459893@163.com
		String to = "15311130895@163.com";
		String subject = "小狗，想我了没啊?";
		String rscId = "yyy";
		String content = "<html>\n" +
	            "<body>\n" +
	            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
	            "<img src=\'cid:" + rscId + "\' >"+
	            "</body>\n" +
	            "</html>";
		String rscPath = "F:\\WeChat\\WeChat Files\\All Users\\f729444eb384b218061f8ffd99830435.jpg";
		mailService.sendInlineResourceMail(to, subject, content, rscPath, rscId);
	}
	
}
