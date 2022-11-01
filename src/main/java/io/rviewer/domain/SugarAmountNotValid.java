package io.rviewer.domain;

public final class SugarAmountNotValid extends OrderException {
    public SugarAmountNotValid() {
        super("sugar_amount_not_valid", "The number of sugars should be between 0 and 2.");
    }
}
