package com.shop.vtluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String resetUrl) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(to);
            messageHelper.setSubject("Reset mật khẩu");
            messageHelper.setText(
                    "<p>Click vào đường dẫn sau để đặt lại mật khẩu:</p> <a href=" + resetUrl
                            + ">Thay đổi mật khẩu</a>" + "<p>Đồng hồ đếm ngược 2 phút:</p>",
                    true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            e.printStackTrace();
        }

    }
}
