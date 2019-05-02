package model.economy;

import model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoanServiceTest {

    @Test
    public void whenALoanIsGivenToAnEligibleUserTheUserHasAThousandPesos(){

        LoanService service = new LoanService();
        User user = new User();

        service.giveLoan(user);

        assertEquals(1000d, user.getMoney(),0);

    }

    //Test cuando no es cumplidor para prestamo.



}
