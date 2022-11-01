package io.rviewer.domain;

public interface OrderOutput {

  void deliver(Order order);

  void inform(OrderException exception);
}
