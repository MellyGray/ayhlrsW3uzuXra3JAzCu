package io.rviewer.domain;

public final class DrinkTypeNotValid extends OrderException {
    public DrinkTypeNotValid() {
        super("drink_type_not_valid", "The drink type should be tea, coffee or chocolate.");
    }
}
