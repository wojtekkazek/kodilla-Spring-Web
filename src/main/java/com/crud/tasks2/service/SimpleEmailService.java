package com.crud.tasks2.service;

import com.crud.tasks2.config.AdminConfig;
import com.crud.tasks2.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;
    @Autowired
    private AdminConfig adminConfig;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
//            SimpleMailMessage mailMessage = createMailMessage(mail);
//            javaMailSender.send(mailMessage);
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

//    32.3
    public void sendDaily(final Mail mail) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(createMimeDailyMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(adminConfig.getAdminMail());
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

//    32.3
    private MimeMessagePreparator createMimeDailyMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(adminConfig.getAdminMail());
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildOnceADayEmail(mail.getMessage()), true);
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
        if (mail.getToCc() != null) {
                mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }

}
