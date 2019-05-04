package model.exceptions;

public class CollectionHasntReachedException extends Exception {
    public CollectionHasntReachedException(Double amountExpected, Double amauntMissing) {
        super("Se necesita recolectar " + amountExpected + " pero aun faltan " + amauntMissing);
    }
}

