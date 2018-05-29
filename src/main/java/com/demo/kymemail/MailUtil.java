package com.demo.kymemail;

import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 发送邮件Util
 */
public class MailUtil {

	// 邮箱
	private static String mailServerHost = "smtp.qq.com";
	private static String mailSenderAddress = "xxx@qq.com";
	private static String mailSenderNick = "xx";
	private static String mailSenderUsername = "xxx@qq.com";
	private static String mailSenderPassword = "*xxxxs";

	/**
	 * 发送 邮件方法 (Html格式，支持附件)
	 * 
	 * @return void
	 * @throws EmailException 
	 */
	public static void sendEmail(MailInfo mailInfo) throws EmailException {

		HtmlEmail email = new HtmlEmail();
		// 配置信息
		email.setHostName(mailServerHost);
		email.setFrom(mailSenderAddress, mailSenderNick);
		email.setAuthentication(mailSenderUsername, mailSenderPassword);
		email.setCharset("UTF-8");
		email.setSubject(mailInfo.getSubject());
		email.setHtmlMsg(mailInfo.getContent());
		email.setSSLOnConnect(true);

		// 添加附件
		List<EmailAttachment> attachments = mailInfo.getAttachments();
		if (null != attachments && attachments.size() > 0) {
			for (int i = 0; i < attachments.size(); i++) {
				email.attach(attachments.get(i));
			}
		}

		// 收件人
		List<String> toAddress = mailInfo.getToAddress();
		if (null != toAddress && toAddress.size() > 0) {
			for (int i = 0; i < toAddress.size(); i++) {
				email.addTo(toAddress.get(i));
			}
		}
		// 抄送人
		List<String> ccAddress = mailInfo.getCcAddress();
		if (null != ccAddress && ccAddress.size() > 0) {
			for (int i = 0; i < ccAddress.size(); i++) {
				email.addCc(ccAddress.get(i));
			}
		}
		// 邮件模板 密送人
		List<String> bccAddress = mailInfo.getBccAddress();
		if (null != bccAddress && bccAddress.size() > 0) {
			for (int i = 0; i < bccAddress.size(); i++) {
				email.addBcc(ccAddress.get(i));
			}
		}
		email.send();
	}
}