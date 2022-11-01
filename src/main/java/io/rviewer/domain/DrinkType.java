package io.rviewer.domain;

import java.util.Arrays;

public final class DrinkType {
    private String value;
    private Float price;

    public DrinkType(String value) {
        isValidType(value);
        
        this.value = value;
        this.price = getPriceByType(value);
    }

    public String value() {
        return value;
    }

    public Float price(){
        return this.price;
    }

    @Override
    public String toString(){
        return value;
    }

    private void isValidType(String value) {
        Boolean isValid = Arrays.asList("COFFEE", "CHOCOLATE", "TEA").contains(value.toUpperCase());
        if (Boolean.FALSE.equals(isValid)) {
            throw new DrinkTypeNotValid();
        }
    }

    private Float getPriceByType(String type) {
        if ("TEA".equalsIgnoreCase(type)) {
            return (float) 0.4;
        }
        if ("COFFEE".equalsIgnoreCase(type)) {
            return (float) 0.5;
        }
        if ("CHOCOLATE".equalsIgnoreCase(type)) {
            return (float) 0.6;
        }
        return (float) 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((price == null) ? 0 : price.hashCode());
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
        DrinkType other = (DrinkType) obj;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
