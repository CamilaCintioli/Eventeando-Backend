package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.mailing;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    String username = "testinterfacesdeusuario@gmail.com";
    String password = "passwordcorreo";

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EmailSender(){}


    //Envia el correo teniendo un unico destinatario.
    public void sendEmail(String receiver, String subject, String text) {
            this.send(Message.RecipientType.TO, receiver, subject, text);
    }


    //Envia el correo teniendo multiples destinatarios como CC, por ende todos los destinatarios seran visibles.
    public void sendEmail(List<String> receivers, String subject, String text) {
            String recipientsAddresses = String.join(",", receivers);
            this.send(Message.RecipientType.CC, recipientsAddresses, subject, text);
    }

    private void send(Message.RecipientType recipientType, String recipientsAddresses, String subject, String text){
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    recipientType,
                    InternetAddress.parse(recipientsAddresses)
            );

            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Session createSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }

}