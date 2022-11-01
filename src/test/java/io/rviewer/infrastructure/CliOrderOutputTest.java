package io.rviewer.infrastructure;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.rviewer.domain.Drink;
import io.rviewer.domain.DrinkPayment;
import io.rviewer.domain.DrinkPaymentNotValid;
import io.rviewer.domain.DrinkType;
import io.rviewer.domain.ExtraHot;
import io.rviewer.domain.Order;
import io.rviewer.domain.Sugar;

public class CliOrderOutputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private CliOrderOutput output = new CliOrderOutput();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void deliver_an_order(){
        Order order = new Order(
                new Drink(
                    new DrinkType("coffee"),
                    new DrinkPayment((float) 1)),
                new Sugar(1),
                new ExtraHot(false)
            );

        output.deliver(order);
        
        assertEquals("You have ordered a coffee with 1 sugars (stick included)", outContent.toString());
    }

    @Test
    public void inform_about_an_order_exception(){
        DrinkType drinkType = new DrinkType("coffee");
        DrinkPaymentNotValid exception = new DrinkPaymentNotValid(drinkType);

        output.inform(exception);
        
        assertEquals("The coffee costs 0.5.", outContent.toString());
    }
}
