package io.rviewer.domain;

public final class DrinkPaymentNotValid extends OrderException {
    public DrinkPaymentNotValid(DrinkType drinkType) {
        super("drink_payment_not_valid", "The " + drinkType.value() + " costs " + drinkType.price() + ".");
    }
}
