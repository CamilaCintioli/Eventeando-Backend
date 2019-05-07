package model.economy;

import model.User;
import model.exceptions.InsufficientFundsException;
import org.junit.Test;


import static org.junit.Assert.*;

public class LoanTest {

    @Test
    public void aLoanShouldHaveSixFeesToBePayed(){

        User user = new User();
        Loan loan = new Loan(user);

        assertEquals(6,loan.getFees().size());
    }

    @Test
    public void aCurrentFeeOfANewLoanShouldBeTheFirstOne(){

        User user = new User();
        Loan loan = new Loan(user);

        assertSame(loan.getFees().get(0),loan.getCurrentFee());
    }

    @Test
    public void whenTheFirstFeeIsPayedTheCurrentFeeIsTheNextOneAndTheUserHas200PesosLess(){

        User user = new User();
        Loan loan = new Loan(user);

        loan.getCurrentFee().charge();

        assertEquals(800d, user.getMoney(),0);
        assertSame(loan.getFees().get(1),loan.getCurrentFee());

    }

    @Test(expected = InsufficientFundsException.class)
    public void whenTheCurrentFeeCanNotBePaidTheCurrentFeeIsTheSame(){

        User user = new User();
        Loan loan = new Loan(user);

        user.extract(900d);

        loan.getCurrentFee().charge();

        assertSame(loan.getFees().get(0),loan.getCurrentFee());
        assertEquals(100d,user.getMoney(),0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void aPendingFeeIsAutomaticallyChargedAfterADeposit(){

        User user = new User();
        Loan loan = new Loan(user);

        user.extract(900d);

        loan.getCurrentFee().charge();

        assertSame(loan.getFees().get(0),loan.getCurrentFee());
        assertEquals(100d,user.getMoney(),0);
        assertTrue(user.isDefaulter());

        user.deposit(100d);

        assertSame(loan.getFees().get(1),loan.getCurrentFee());
        assertEquals(0d, user.getMoney(),0);

    }




}
