package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

public class TransferenceDTO {


    String emailSender;
    String emailReceiver;
    double amount;

    public TransferenceDTO(){}

    public TransferenceDTO(String emailSender, String emailReceiver, double amount){
        this.emailReceiver= emailReceiver;
        this.emailSender=emailSender;
        this.amount = amount;
    }

    public String getEmailSender() {
        return this.emailSender;
    }

    public String getEmailReceiver() {
        return this.emailReceiver;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public void setEmailReceiver(String emailReceiver) {
        this.emailReceiver = emailReceiver;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
