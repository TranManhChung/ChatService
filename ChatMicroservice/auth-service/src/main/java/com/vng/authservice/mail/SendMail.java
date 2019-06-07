//package com.vng.authservice.mail;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Service
//public class SendMail {
//    @Autowired
//    private static JavaMailSender mailSender;
//
//    public static boolean sendHtmlTemplate(String mail, String token, int type) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        boolean mutilpart = true;
//        MimeMessageHelper helper = new MimeMessageHelper(message, mutilpart, "UTF-8");
//        String html = "";
//
//        if (type == 1){
//            String link = "localhost:8086/confirmregister?token=" + token;
//            html = "<div><a href='mailto:" + mail + "' target='_blank'>" + mail + "</a><br>" +
//                    "\t\t\t\t\t\t\t\t- Your account: <a href='mailto:" + mail + "' target='_blank'> " + mail + "</a><br>\n" +
//                    "\t\t\t\t\t\t\t\t- Please click <a href='http://" + link + "'>here</a> to confirm register for account <a href='mailto:" + mail + "' target='_blank'>" + mail + "</a>:<br>\n" +
//                    "\t\t\t\t\t\t\t\t- If you don't request, please ignore this mail<br>\n" +
//                    "\t\t\t\t\t\t\t\t<br><br>\n" +
//                    "\t\t\t\t\t\t\t\t\n" +
//                    "\t\t\t\t\t\t\t\t- Don't reply this mail.<br>\n" +
//                    "</div>";
//        } else if (type == 2) {
//            String link = "localhost:8086/confirmchangepassword?token=" + token;
//            html = "<div><a href='mailto:" + mail + "' target='_blank'>" + mail + "</a><br>" +
//                    "\t\t\t\t\t\t\t\t- Your account: <a href='mailto:" + mail + "' target='_blank'> " + mail + "</a><br>\n" +
//                    "\t\t\t\t\t\t\t\t- Please click <a href='http://" + link + "'>here</a> to change your password<a href='mailto:" + mail + "' target='_blank'>" + mail + "</a>:<br>\n" +
//                    "\t\t\t\t\t\t\t\t- If you don't request, please ignore this mail<br>\n" +
//                    "\t\t\t\t\t\t\t\t<br><br>\n" +
//                    "\t\t\t\t\t\t\t\t\n" +
//                    "\t\t\t\t\t\t\t\t- Don't reply this mail.<br>\n" +
//                    "</div>";
//        }
//
//        message.setContent(html, "text/html");
//        helper.setTo(mail);
//        helper.setSubject("Confirm Register");
//
//        mailSender.send(message);
//        return true;
//    }
//}
