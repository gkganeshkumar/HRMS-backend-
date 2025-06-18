package com.example.Invoice_Hrms.controller;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@RequestMapping("/api/email")
@RestController
public class mailController {


    private final JavaMailSender mailSender;

    public mailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping("/send-email")
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("wareacent@gmail.com");
            message.setTo("wareacent@gmail.com");
            message.setSubject("Simple test email from Invoice!");
            message.setText("This is a invoice for Ascentware!");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("wareacent@gmail.com");
            helper.setTo("wareacent@gmail.com");
            helper.setSubject("Java email with attachment");
            helper.setText("Please find the attached documents below");

            helper.addAttachment("logo.png", new File("C:\\Users\\ganesh\\Downloads\\“We Are Hiring.png"));
            helper.addAttachment("presentation.pdf", new File("C:\\Users\\ganesh\\Downloads\\booking.pdf"));

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
