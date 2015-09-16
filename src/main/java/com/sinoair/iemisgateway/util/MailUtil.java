package com.sinoair.iemisgateway.util;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangMJ
 * Date: 15-9-9
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class MailUtil {
    final static String mailhost = "mail.sinoair.com";

    /**
     * 发送邮件工具类
     *
     * @param to 收件人
     * @param cc 抄送
     * @param bcc 待定
     * @param subject 主题
     * @param message 邮件正文
     * @param from 发件人邮箱
     * @param csvFileList 附件
     * @param username 用户名
     * @param password 密码
     * @throws MessagingException
     * @throws IOException
     */
    public static void postMail(String to, String cc, String bcc, String subject, String message, String from, ArrayList<File> csvFileList, String username, String password) throws MessagingException, IOException {
        boolean debug = false;
        boolean auth = true;
        Properties props = System.getProperties();
        if (mailhost != null)
            props.put("mail.smtp.host", mailhost);
        if (auth)
            props.put("mail.smtp.auth", "true");
        // Get a Session object
        Session session = Session.getInstance(props, null);
        if (debug)
            session.setDebug(true);
        Message msg = new MimeMessage(session);
        if (from != null)
            msg.setFrom(new InternetAddress(from));
        else
            msg.setFrom();
        /**支持给多人发送邮件，收件人邮件地址以空格分隔 by wangsen 2014-2-24*/
        InternetAddress[] toAddrs = InternetAddress.parse(to, false);
        msg.setRecipients(Message.RecipientType.TO, toAddrs);

        if (cc != null)
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(cc, false));
        if (bcc != null)
            msg.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(bcc, false));
        msg.setSubject(subject);

            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(message);
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
         if (csvFileList != null && csvFileList.size() > 0) {
            for (int i = 0; i < csvFileList.size(); i++) {
                File file = (File) csvFileList.get(i);
                MimeBodyPart mbp2 = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(file);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(MimeUtility.encodeWord(fds.getName(), "GB2312", null));
                mp.addBodyPart(mbp2);

            }
           }
            msg.setContent(mp);

        String mailer = "smtpsend";
        msg.setHeader("X-Mailer", mailer);
        msg.setSentDate(new Date());
        SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
        try {
            if (auth)
                t.connect(mailhost, username, password);
            else
                t.connect();
            t.sendMessage(msg, msg.getAllRecipients());
        } finally {

            t.close();
        }

    }
}
