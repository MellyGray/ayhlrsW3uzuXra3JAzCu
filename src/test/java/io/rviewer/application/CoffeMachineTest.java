package io.rviewer.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import io.rviewer.domain.OrderOutput;
import io.rviewer.domain.Drink;
import io.rviewer.domain.DrinkPayment;
import io.rviewer.domain.DrinkPaymentNotValid;
import io.rviewer.domain.DrinkType;
import io.rviewer.domain.DrinkTypeNotValid;
import io.rviewer.domain.ExtraHot;
import io.rviewer.domain.Order;
import io.rviewer.domain.Sugar;
import io.rviewer.domain.SugarAmountNotValid;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

@RunWith(DataProviderRunner.class)
public class CoffeMachineTest {
    private CoffeeMachine coffeeMachine;
    private OrderOutput output;

    @Before
    public void setUp() {
        output = mock(OrderOutput.class);
        coffeeMachine = new CoffeeMachine(output);
    }

    @Test
    @UseDataProvider("drinkValidCombinations")
    public void request_a_valid_order(CoffeeMachineRequest request) {
        Order order = new Order(
                new Drink(
                    new DrinkType(request.drinkType()),
                    new DrinkPayment(request.money())),
                new Sugar(request.sugar()),
                new ExtraHot(request.extraHot())
            );

        coffeeMachine.makeDrink(request);

        verify(output, atLeastOnce()).deliver(order);
    }

    @Test
    @UseDataProvider("drinkNotValidPaymentCombinations")
    public void request_a_non_valid_order_with_wrong_payment(CoffeeMachineRequest request) {
        DrinkType drinkType = new DrinkType(request.drinkType());
        DrinkPaymentNotValid exception = new DrinkPaymentNotValid(drinkType);

        coffeeMachine.makeDrink(request);

        verify(output, atLeastOnce()).inform(exception);
    }

    @Test
    public void request_a_non_valid_order_with_wrong_drink_type() {
        CoffeeMachineRequest request = new CoffeeMachineRequest("foo", (float) 0.1, 1, false);
        DrinkTypeNotValid exception = new DrinkTypeNotValid();

        coffeeMachine.makeDrink(request);

        verify(output, atLeastOnce()).inform(exception);
    }

    @Test
    public void request_a_non_valid_order_with_wrong_sugar_amount() {
        CoffeeMachineRequest request = new CoffeeMachineRequest("coffee", (float) 1, 5, false);
        SugarAmountNotValid exception = new SugarAmountNotValid();

        coffeeMachine.makeDrink(request);

        verify(output, atLeastOnce()).inform(exception);
    }

    @DataProvider
    public static Object[][] drinkValidCombinations() {
        return new Object[][]{
                {new CoffeeMachineRequest("coffee", (float) 1, 1, false)},
                {new CoffeeMachineRequest("coffee", (float) 1, 1, true)},
                {new CoffeeMachineRequest("coffee", (float) 1, 0, false)},
                
                {new CoffeeMachineRequest("tea", (float) 1, 1, false)},
                {new CoffeeMachineRequest("tea", (float) 1, 1, true)},
                {new CoffeeMachineRequest("tea", (float) 1, 0, false)},
                
                {new CoffeeMachineRequest("chocolate", (float) 1, 1, false)},
                {new CoffeeMachineRequest("chocolate", (float) 1, 1, true)},
                {new CoffeeMachineRequest("chocolate", (float) 1, 0, false)},
        };
    }

    @DataProvider
    public static Object[][] drinkNotValidPaymentCombinations() {
        return new Object[][]{
                {new CoffeeMachineRequest("coffee", (float) 0.1, 1, false)},

                {new CoffeeMachineRequest("tea", (float) 0.1, 1, false)},

                {new CoffeeMachineRequest("chocolate", (float) 0.1, 1, false)},
        };
    }
}
