package io.rviewer.domain;

public final class Sugar {
    private Integer value;

    public Sugar(Integer value) {
        isValidAmount(value);

        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        String sugarString = " with " + value + " sugar";

        if (value > 0) {
            sugarString += "s (stick included)";
        }

        return sugarString;
    }

    private void isValidAmount(Integer value) {
        if (value < 0 || value > 2) {
            throw new SugarAmountNotValid();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Sugar other = (Sugar) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
