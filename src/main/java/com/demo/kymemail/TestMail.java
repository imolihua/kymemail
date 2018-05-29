package com.demo.kymemail;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

public class TestMail {

	/**
	 * @return void
	 * @throws EmailException 
	 */
	public static void main(String[] args) throws EmailException {
		MailInfo mailInfo = new MailInfo();
		List<String> toList = new ArrayList<String>();
		toList.add("xxx@yyy.com");

		List<String> ccList = new ArrayList<String>();
		ccList.add("xxx@yyyy.com");

		List<String> bccList = new ArrayList<String>();
		bccList.add("xxx@yyyy.com");

		// 添加附件
		EmailAttachment att = new EmailAttachment();
		att.setPath("d:\\test.txt");
		att.setName("test.txt");
		List<EmailAttachment> atts = new ArrayList<EmailAttachment>();
		atts.add(att);
		mailInfo.setAttachments(atts);

		mailInfo.setToAddress(toList);// 收件人
		mailInfo.setCcAddress(ccList);// 抄送人
		mailInfo.setBccAddress(bccList);// 密送人

		mailInfo.setSubject("测试主题");
		mailInfo.setContent("内容：<h1>test,测试</h1>");

		MailUtil.sendEmail(mailInfo);
		System.out.println("邮件发送成功！");

	}
}