package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.economy;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static java.util.Objects.isNull;

@Entity
public class Movement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementType movementType;
    @Column
    private String user;
    @Column
    private double ammount;
    @Transient
    private Date date;

    public Movement(){}

    public Movement(double ammount, MovementType movementType) {
        this.ammount = ammount;
        this.movementType = movementType;
        this.date = new Date();
    }

    public Movement(double ammount, MovementType movementType, String user) {
        this.ammount = ammount;
        this.movementType = movementType;
        this.user = user;
        this.date = new Date();
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public String getUser() {
        return user;
    }

    public double getAmmount() {
        return ammount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Double.compare(movement.ammount, ammount) == 0 &&
                movementType == movement.movementType &&
                (isNull(this.user) || user.equals(movement.user));
    }

    @Override
    public int hashCode() {
        return Objects.hash(movementType, user, ammount);
    }
}
