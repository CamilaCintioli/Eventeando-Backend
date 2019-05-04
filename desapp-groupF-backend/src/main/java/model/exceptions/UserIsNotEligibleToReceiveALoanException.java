package model.exceptions;

public class UserIsNotEligibleToReceiveALoanException extends RuntimeException {

    public UserIsNotEligibleToReceiveALoanException(){ super("The user is not eligible to recieve a loan");}

}
