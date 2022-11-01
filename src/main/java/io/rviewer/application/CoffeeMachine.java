package io.rviewer.application;

import io.rviewer.domain.OrderOutput;
import io.rviewer.domain.Drink;
import io.rviewer.domain.DrinkPayment;
import io.rviewer.domain.DrinkType;
import io.rviewer.domain.ExtraHot;
import io.rviewer.domain.Order;
import io.rviewer.domain.OrderException;
import io.rviewer.domain.Sugar;

public final class CoffeeMachine {
  private OrderOutput output;

  public CoffeeMachine(OrderOutput output) {
    this.output = output;
  }

  public void makeDrink(CoffeeMachineRequest request) {
    try {
      Order order = new Order(
          new Drink(
              new DrinkType(request.drinkType()),
              new DrinkPayment(request.money())),
          new Sugar(request.sugar()),
          new ExtraHot(request.extraHot()));

      output.deliver(order);
    } catch (OrderException exception) {
      output.inform(exception);
    }
  }
}
