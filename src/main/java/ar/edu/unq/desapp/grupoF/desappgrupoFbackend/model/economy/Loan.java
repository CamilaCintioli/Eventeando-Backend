package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.Product;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Column
    String userEmail;
    @OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    private Fee currentFee;
    @OneToMany(fetch= FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Fee> fees;

    public Loan(){}

    public Loan(String userEmail, Account account) {
        this.userEmail = userEmail;
        this.fees = Arrays.asList(new Fee(userEmail), new Fee(userEmail), new Fee(userEmail), new Fee(userEmail), new Fee(userEmail), new Fee(userEmail));


        account.transferInto(1000d,null);

    }

    public Fee getCurrentFee() {
        for(Fee fee:fees){

            if(!fee.isPaid()) return fee;
        }

        return null;
    }

    public List<Fee> getFees() {
        return this.fees;
    }
}
