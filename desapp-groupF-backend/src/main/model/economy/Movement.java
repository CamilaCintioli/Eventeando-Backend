package model.economy;

import model.User;

import java.util.Date;
import java.util.Objects;

import static java.util.Objects.isNull;

public class Movement {

    private MovementType movementType;
    private User user;
    private double ammount;
    private Date date;

    public Movement(double ammount, MovementType movementType) {
        this.ammount = ammount;
        this.movementType = movementType;
        this.date = new Date();
    }

    public Movement(double ammount, MovementType movementType, User user) {
        this.ammount = ammount;
        this.movementType = movementType;
        this.user = user;
        this.date = new Date();
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public User getUser() {
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
