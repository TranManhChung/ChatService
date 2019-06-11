package com.vng.authservice.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

public class SendMail {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendHtmlTemplate(String mail, String token) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        boolean mutilpart = true;
//        MimeMessageHelper helper = new MimeMessageHelper(message, mutilpart, "utf-8");
//
//        String html = "<div><a href=\"mailto:" + mail + "\" target=\"_blank\">" + mail + "</a><br>" +
//                "\t\t\t\t\t\t\t\t- Tài khoản của bạn là: <a href=\"mailto:" + mail + "\" target=\"_blank\"> " + mail + "</a><br>\n" +
//                "\t\t\t\t\t\t\t\tBạn vui lòng nhấp vào link sau để kích hoạt cho tài khoản <a href=\"mailto:" + mail + "\" target=\"_blank\">" + mail + "</a>:<br>\n" +
//                "\t\t\t\t\t\t\t\t<a href=\"http:localhost:8086/confirmregister\"></a>" +
//                "\t\t\t\t\t\t\t\tLưu ý: nếu bạn không yêu cầu cấp tài khoản, vui lòng bỏ qua email này và bảo mật thông tin tài khoản của bạn<br>\n" +
//                "\t\t\t\t\t\t\t\t<br><br>\n" +
//                "\t\t\t\t\t\t\t\t\n" +
//                "\t\t\t\t\t\t\t\tVui lòng không trả lời email này.<br>\n" +
//                "</div>";
//
//        message.setContent(html, "text/html");
//        helper.setTo(mail);
//        helper.setSubject("Thư xác nhận cấp tài khoản");
//
//        mailSender.send(message);

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail);
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        // Send Message!
        mailSender.send(message);

        return true;
    }
}
