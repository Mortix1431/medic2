package com.example.medic;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... mail_str) {
        Log.e("Log", "Лог: "+ mail_str[1]);
        final String username = "aleksandarnosenko";
        final String password = "Zxcvbnm78";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.yandex.ru");
        prop.put("mail.transport.protocol", "smtps");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.user", "aleksandarnosenko");
        prop.put("mail.smtp.ssl.enable", "true"); //TLS
        prop.put("mail.debug", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //System.out.println(mail_str[0]);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aleksandarnosenko@yandex.ru"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail_str[0])
            );
            message.setSubject("Проверочный код для Medic");
            //Random rand = new Random();
            String code = mail_str[1];
            message.setText("Ваш код проверки: "+ code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
