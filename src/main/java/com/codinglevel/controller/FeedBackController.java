package com.codinglevel.controller;

import com.codinglevel.configuration.EmailConfiguration;
import com.codinglevel.entities.FeedBackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequestMapping(value = "/email")
public class FeedBackController {

    @Autowired
    private EmailConfiguration emailConfiguration;

    @PostMapping(value = "/send")
    public String sendEmail(@RequestBody FeedBackEntity feedBackEntity,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationException("FeedBack is not valid");
        }

        // Creat a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfiguration.getHost());
        mailSender.setPort(this.emailConfiguration.getPort());
        mailSender.setUsername(this.emailConfiguration.getUsername());
        mailSender.setPassword(this.emailConfiguration.getPassword());

        // Creat an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedBackEntity.getEmail());
        mailMessage.setTo("atalibletouareg@gmail.com");
        mailMessage.setSubject("Coding message from " + feedBackEntity.getName());
        mailMessage.setText(feedBackEntity.getMessage());

        //Send the message
        mailSender.send(mailMessage);

        return "Thank For Your Feed Back Email Send Success";
    }
}
