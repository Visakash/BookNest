package com.crni99.bookstore.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

class EmailServiceTest {

    private JavaMailSender mailSender = mock(JavaMailSender.class);
    private EmailService emailService = new EmailService();

    @Test
    void sendEmailTest() {
        String to = "visputea092@gmail.com";
        String subject = "Test subject";
        String message = "Test message body.";

        // Call the method under test
        emailService.sendEmail(to, subject, message);

        // Capture the argument passed to mailSender.send()
        ArgumentCaptor<SimpleMailMessage> mailCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(mailCaptor.capture());

        // Get the captured mail message
        SimpleMailMessage sentMail = mailCaptor.getValue();

        // Assertions to verify email properties
        assertEquals(to, sentMail.getTo()[0]);
        assertEquals(subject, sentMail.getSubject());
        assertEquals(message, sentMail.getText());
    }
}
