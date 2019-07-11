package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.mailing;

public class Invitation {

    public static String invitationMessage(String from, String to){

        return "Hola " + to +  "! Estamos felices de contarte que " + from + " te invitó para que formes parte de su evento! Por favor dirigite a Eventeando e ingresa para " +
                "poder confirmar tu asistencia. Formando parte de Eventeando también podés crear tus propios eventos, gestionando tus invitados, productos necesarios y gastos en la misma " +
                "pagina. PASALO LINDO!!";

    }

}
