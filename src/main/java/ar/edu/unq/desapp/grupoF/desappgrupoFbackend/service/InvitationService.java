package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.service;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;
import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.mailing.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvitationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public InvitationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendInvitations(List<String> mails, String from) throws MailException, InterruptedException {

       for(String guestMail: mails){

           System.out.println("Sending email...");

           SimpleMailMessage mail = new SimpleMailMessage();
           mail.setTo(guestMail);
           mail.setFrom("testinterfacesdeusuario@gmail.com");
           mail.setSubject("Invitación a evento!");
           mail.setText(Invitation.invitationMessage(from, guestMail));
           javaMailSender.send(mail);

           System.out.println("Email Sent!");

       }




    }

}