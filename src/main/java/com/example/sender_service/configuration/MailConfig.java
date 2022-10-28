package com.example.sender_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private final Environment environment;

    public MailConfig(Environment environment) {
        this.environment = environment;
    }

    public Properties properties(){
        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.debug", environment.getProperty("mail.debug"));
        properties.setProperty("mail.smtp.socketFactory.port", environment.getProperty("mail.smtp.socketFactory.port"));
        properties.setProperty("mail.smtp.socketFactory.class", environment.getProperty("mail.smtp.socketFactory.class"));
        properties.setProperty("mail.smtp.ssl.enable", environment.getProperty("mail.smtp.ssl.enable"));

        return properties;
    }


    @Bean
    JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(properties());
        mailSender.setHost(environment.getProperty("mail.host_name"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
        mailSender.setUsername(environment.getProperty("mail.user_name"));
        mailSender.setPassword(environment.getProperty("mail.user_password"));
        mailSender.setProtocol(environment.getProperty("mail.protocol"));
        return mailSender;
    }
}
