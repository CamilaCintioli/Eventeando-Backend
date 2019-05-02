package model.economy;

import model.User;
import model.economy.Account;
import model.economy.Movement;
import model.economy.MovementType;
import model.exceptions.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    Account account = new Account();

    @Test
    public void anAccountHasTenPesos(){

        account.setBalance(10d);

        assertEquals(10d,account.getBalance(),0);
    }

    @Test
    public void TenPesosCanBeDepositedIntoAnAccount(){

        account.deposit(10d);

        assertEquals(10d,account.getBalance(),0);
    }

    @Test
    public void TenPesosCanBeExtractedFromAnAccount(){

        account.setBalance(50d);

        account.extract(10d);

        assertEquals(40d,account.getBalance(),0);

    }

    @Test(expected = InsufficientFundsException.class)
    public void TwentyPesosCantBeExtractedFromAnAccountWIthTenPesos(){

        account.setBalance(10d);

        account.extract(20d);
    }

    @Test
    public void TenPesosCanBeTransferedFromAnAccountAToAnAccountB(){

        account.deposit(70d);

        Account accountB = new Account();

        User userA = new User();
        User userB = new User();
        userA.setAccount(account);
        userB.setAccount(accountB);

        assertEquals(0, accountB.getBalance(),0);

        account.transferTo(10d,userB,userA);

        assertEquals(10d,accountB.getBalance(),0);
        assertEquals(60d,account.getBalance(),0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void TenPesosCantBeTransferedFromAnAccountAWithCeroPesosToAnAccountB(){

        Account accountB = new Account();

        User userA = new User();
        User userB = new User();
        userA.setAccount(account);
        userB.setAccount(accountB);

        assertEquals(0, accountB.getBalance(),0);

        account.transferTo(10d,userB,userA);
    }

    //MOVEMENT HISTORY.

    @Test
    public void AfterADepositItHasToBeRepresentedInTheAccountHistory() {
        account.deposit(10d);

        Movement expected = new Movement(10d, MovementType.DEPOSIT);
        assertEquals(expected, account.getHistory().get(0));
    }

    @Test
    public void AfterAnExtractionItHasToBeRepresentedInTheAccountHistory() {
        account.setBalance(10d);
        account.extract(10d);

        Movement expected = new Movement(10d, MovementType.EXTRACTION);
        assertEquals(expected, account.getHistory().get(0));
    }

    @Test
    public void AfterATransferToItHasToBeRepresentedInTheAccountHistory() {
        account.setBalance(10d);
        Account accountB = new Account();

        User userA = new User();
        User userB = new User();
        userA.setAccount(account);
        userB.setAccount(accountB);

        Movement expected = new Movement(10d, MovementType.TRANSFERTO, userB);
        assertEquals(0, accountB.getBalance(),0);


        account.transferTo(10d,userB,userA);

        assertEquals(expected, account.getHistory().get(0));
    }

    @Test
    public void AfterATransferIntoItHasToBeRepresentedInTheAccountHistory() {
        account.setBalance(10d);
        Account accountB = new Account();

        User userA = new User();
        User userB = new User();
        userA.setAccount(account);
        userB.setAccount(accountB);

        Movement expected = new Movement(10d, MovementType.TRANSFERINTO, userA);
        assertEquals(0, accountB.getBalance(),0);

        account.transferTo(10d,userB,userA);

        assertEquals(expected, accountB.getHistory().get(0));
    }

}
