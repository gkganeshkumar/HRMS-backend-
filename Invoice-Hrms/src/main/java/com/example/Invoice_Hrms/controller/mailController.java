package com.example.Invoice_Hrms.controller;

import com.example.Invoice_Hrms.service.InvoiceEmailService;


import com.example.Invoice_Hrms.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class mailController {


    private final InvoiceEmailService invoiceEmailService;
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmai1l(
            @RequestParam("invoiceNo") String invoiceNo,
            @RequestParam("pdf") MultipartFile pdf) {

        try {
            byte[] pdfBytes = pdf.getBytes();
            String recipientEmail = invoiceEmailService.getEmailByInvoiceNo(invoiceNo);
            if (recipientEmail == null || recipientEmail.isEmpty()) {
                return ResponseEntity.badRequest().body("No email found for invoiceNo: " + invoiceNo);
            }

            invoiceEmailService.sendInvoiceWithAttachment(invoiceNo, pdfBytes, recipientEmail);

            return ResponseEntity.ok("Email sent successfully to " + recipientEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email sending failed.");
        }
    }
    @PostMapping("/send-email1")
    public ResponseEntity<String> sendEmail(
            @RequestParam String invoiceNo,
            @RequestParam("file") MultipartFile pdf) {
        try {
            byte[] pdfBytes = pdf.getBytes();


            String recipientEmail = invoiceEmailService.getEmailByInvoiceNo(invoiceNo);
            if (recipientEmail == null || recipientEmail.isEmpty()) {
                return ResponseEntity.badRequest().body("No email found for invoiceNo: " + invoiceNo);
            }

            invoiceEmailService.sendInvoiceWithAttachment(invoiceNo, pdfBytes, recipientEmail);

            return ResponseEntity.ok("Email sent successfully to " + recipientEmail);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }



}













//
//    public mailController(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    @RequestMapping("/send-email")
//    public String sendEmail() {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//
//            message.setFrom("wareacent@gmail.com");
//            message.setTo("wareacent@gmail.com");
//            message.setSubject("Simple test email from Invoice!");
//            message.setText("This is a invoice for Ascentware!");
//
//            mailSender.send(message);
//            return "success!";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping("/send-email-with-attachment")
//    public String sendEmailWithAttachment() {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setFrom("wareacent@gmail.com");
//            helper.setTo("wareacent@gmail.com");
//            helper.setSubject("Java email with attachment");
//            helper.setText("Please find the attached documents below");
//
//            helper.addAttachment("logo.png", new File("C:\\Users\\ganesh\\Downloads\\“We Are Hiring.png"));
//            helper.addAttachment("presentation.pdf", new File("C:\\Users\\ganesh\\Downloads\\booking.pdf"));
//
//            mailSender.send(message);
//            return "success!";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
