package io.rviewer.domain;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import io.rviewer.application.CoffeeMachineRequest;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;

@RunWith(DataProviderRunner.class)
public class OrderTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    @UseDataProvider("drinkValidCombinations")
    public void order_valid_combinations(CoffeeMachineRequest request, String expectedMessage) {
        Order order = new Order(
                new Drink(
                    new DrinkType(request.drinkType()),
                    new DrinkPayment(request.money())),
                new Sugar(request.sugar()),
                new ExtraHot(request.extraHot())
            );

        assertEquals(order.toString(), expectedMessage);
    }

    @Test
    @UseDataProvider("drinkNotValidPaymentCombinations")
    public void order_with_non_valid_payment_combinations(CoffeeMachineRequest request, String expectedMessage) {
        exceptionRule.expect(DrinkPaymentNotValid.class);
        exceptionRule.expectMessage(expectedMessage);

        new Order(
                new Drink(
                        new DrinkType(request.drinkType()),
                        new DrinkPayment(request.money())),
                new Sugar(request.sugar()),
                new ExtraHot(request.extraHot()));
    }

    @Test
    public void order_with_non_valid_drink_type() {
        exceptionRule.expect(DrinkTypeNotValid.class);
        exceptionRule.expectMessage("The drink type should be tea, coffee or chocolate.");

        CoffeeMachineRequest request = new CoffeeMachineRequest("foo", (float) 0.1, 1, false);

        new Order(
                new Drink(
                        new DrinkType(request.drinkType()),
                        new DrinkPayment(request.money())),
                new Sugar(request.sugar()),
                new ExtraHot(request.extraHot()));
    }

    @Test
    public void order_with_non_valid_sugar_amount() {
        exceptionRule.expect(SugarAmountNotValid.class);
        exceptionRule.expectMessage("The number of sugars should be between 0 and 2.");

        CoffeeMachineRequest request = new CoffeeMachineRequest("coffee", (float) 1, 5, false);

        new Order(
                new Drink(
                        new DrinkType(request.drinkType()),
                        new DrinkPayment(request.money())),
                new Sugar(request.sugar()),
                new ExtraHot(request.extraHot()));
    }

    @DataProvider
    public static Object[][] drinkValidCombinations() {
        return new Object[][]{
                {new CoffeeMachineRequest("coffee", (float) 1, 1, false), "You have ordered a coffee with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("coffee", (float) 1, 1, true), "You have ordered a coffee extra hot with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("coffee", (float) 1, 0, false), "You have ordered a coffee with 0 sugar"},
                
                {new CoffeeMachineRequest("tea", (float) 1, 1, false), "You have ordered a tea with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("tea", (float) 1, 1, true), "You have ordered a tea extra hot with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("tea", (float) 1, 0, false), "You have ordered a tea with 0 sugar"},
                
                {new CoffeeMachineRequest("chocolate", (float) 1, 1, false), "You have ordered a chocolate with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("chocolate", (float) 1, 1, true), "You have ordered a chocolate extra hot with 1 sugars (stick included)"},
                {new CoffeeMachineRequest("chocolate", (float) 1, 0, false), "You have ordered a chocolate with 0 sugar"},
        };
    }

    @DataProvider
    public static Object[][] drinkNotValidPaymentCombinations() {
        return new Object[][]{
                {new CoffeeMachineRequest("coffee", (float) 0.1, 1, false), "The coffee costs 0.5."},

                {new CoffeeMachineRequest("tea", (float) 0.1, 1, false), "The tea costs 0.4."},

                {new CoffeeMachineRequest("chocolate", (float) 0.1, 1, false), "The chocolate costs 0.6."},
        };
    }
}
