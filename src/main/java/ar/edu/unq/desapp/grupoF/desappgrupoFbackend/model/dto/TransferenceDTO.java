package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.dto;

import java.util.regex.Pattern;

public class TransferenceDTO {


    String emailSender;
    String emailReceiver;
    Double amount;

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

    public Double getAmount() {
        return this.amount;
    }

    public void setEmailSender(String emailSender) {
        if(emailSender!=null && this.checkEmail(emailSender)){
            this.emailSender = emailSender;
        }
        else {
            throw new RuntimeException("The email sender should be valid");
        }

    }

    public void setEmailReceiver(String emailReceiver) {

        if(emailReceiver!=null && this.checkEmail(emailReceiver)){
            this.emailReceiver = emailReceiver;
        }
        else {
            throw new RuntimeException("The email receiver should be valid");
        }

    }

    public void setAmount(Double amount) {
        if(amount==null){
            throw new RuntimeException("There must be an amount of money to transfer");
        }
        this.amount = amount;
    }

    private boolean checkEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
