package io.rviewer.application;

public final class CoffeeMachineRequest {
    private String drinkType;
    private Float money;
    private Integer sugar;
    private Boolean extraHot;
    
    public CoffeeMachineRequest(String drinkType, Float money, Integer sugar, Boolean extraHot) {
        this.drinkType = drinkType;
        this.money = money;
        this.sugar = sugar;
        this.extraHot = extraHot;
    }

    public String drinkType() {
        return drinkType;
    }

    public Float money() {
        return money;
    }

    public Integer sugar() {
        return sugar;
    }

    public Boolean extraHot() {
        return extraHot;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((drinkType == null) ? 0 : drinkType.hashCode());
        result = prime * result + ((extraHot == null) ? 0 : extraHot.hashCode());
        result = prime * result + ((money == null) ? 0 : money.hashCode());
        result = prime * result + ((sugar == null) ? 0 : sugar.hashCode());
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
        CoffeeMachineRequest other = (CoffeeMachineRequest) obj;
        if (drinkType == null) {
            if (other.drinkType != null)
                return false;
        } else if (!drinkType.equals(other.drinkType))
            return false;
        if (extraHot == null) {
            if (other.extraHot != null)
                return false;
        } else if (!extraHot.equals(other.extraHot))
            return false;
        if (money == null) {
            if (other.money != null)
                return false;
        } else if (!money.equals(other.money))
            return false;
        if (sugar == null) {
            if (other.sugar != null)
                return false;
        } else if (!sugar.equals(other.sugar))
            return false;
        return true;
    }
}
