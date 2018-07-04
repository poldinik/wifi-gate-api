package com.cosmink.services.emails;

import com.cosmink.models.email.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response;
import java.util.Properties;

public class EmailSender {

    public static Response sendEmail(Email email){

        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", 587);


        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(email.getFrom(), email.getPassFrom());
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lorenzo.vannucchi1993@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());

            //Invio messaggio del webhook per testarlo ed avere id ultimo messaggio mandato
            message.setContent(email.getContent(),"text/html");
            Transport.send(message);
            System.out.println("Messaggio inviato correttamente");


        } catch (MessagingException mex){
            mex.printStackTrace();

        }

        return Response.ok().build();
    }
}
