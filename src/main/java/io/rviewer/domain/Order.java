package io.rviewer.domain;

public final class Order {
    private Drink drink;
    private Sugar suggar;
    private ExtraHot extraHot;
    
    public Order(Drink drink, Sugar suggar, ExtraHot extraHot) {
        this.drink = drink;
        this.suggar = suggar;
        this.extraHot = extraHot;
    }

    public Drink drink() {
        return drink;
    }

    public Sugar suggar() {
        return suggar;
    }

    public ExtraHot extraHot() {
        return extraHot;
    }

    @Override
    public String toString() {
        return "You have ordered a " + drink + extraHot + suggar;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((drink == null) ? 0 : drink.hashCode());
        result = prime * result + ((extraHot == null) ? 0 : extraHot.hashCode());
        result = prime * result + ((suggar == null) ? 0 : suggar.hashCode());
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
        Order other = (Order) obj;
        if (drink == null) {
            if (other.drink != null)
                return false;
        } else if (!drink.equals(other.drink))
            return false;
        if (extraHot == null) {
            if (other.extraHot != null)
                return false;
        } else if (!extraHot.equals(other.extraHot))
            return false;
        if (suggar == null) {
            if (other.suggar != null)
                return false;
        } else if (!suggar.equals(other.suggar))
            return false;
        return true;
    }
}
