package io.rviewer.domain;

public final class Drink {
    private DrinkType type;
    private DrinkPayment payment;

    public Drink(DrinkType type, DrinkPayment payment) {
        isValidPayment(type, payment);

        this.type = type;
        this.payment = payment;
    }

    public DrinkType type() {
        return type;
    }

    public DrinkPayment payment() {
        return payment;
    }

    private void isValidPayment(DrinkType type, DrinkPayment payment) {
        if (payment.value() < type.price()) {
            throw new DrinkPaymentNotValid(type);
        }
    }

    @Override
    public String toString(){
        return type.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Drink other = (Drink) obj;
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
