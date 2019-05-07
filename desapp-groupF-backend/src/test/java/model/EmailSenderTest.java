package model;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EmailSenderTest {
    EmailSender emailSender = new EmailSender("testinterfacesdeusuario@gmail.com", "passwordcorreo");

    @Test
    public void EmailSenderCanSendAEmailToAOneAddressee() {
        emailSender.sendEmail("urielintemperieoficial@gmail.com","Test envio email", "Esto es solo un test del envio de un correo");
    }

    @Test
    public void EmailSenderCanSendAEmailToMultipleAddresses() {
        List<String> destinatarios = Arrays.asList("urielintemperie@gmail.com","urielintemperieoficial@gmail.com");
        emailSender.sendEmail(destinatarios,"Test envio email Multiple", "Esto es solo un test del envio de un correo");
    }

}
