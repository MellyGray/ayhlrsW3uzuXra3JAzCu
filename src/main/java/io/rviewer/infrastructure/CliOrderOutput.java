package io.rviewer.infrastructure;

import io.rviewer.domain.OrderOutput;
import io.rviewer.domain.Order;
import io.rviewer.domain.OrderException;

public class CliOrderOutput implements OrderOutput {

  @Override
  public void deliver(Order order) {
    System.out.print(order.toString());
  }

  @Override
  public void inform(OrderException exception) {
    System.out.print(exception.getMessage());
  }
}
