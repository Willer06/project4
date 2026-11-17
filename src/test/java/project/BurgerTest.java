package project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests single- and double-patty burger prices with toppings.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class BurgerTest {
    /** Verifies a single-patty burger with lettuce. */
    @Test
    public void testSinglePattyWithLettuce() {
        Burger burger = new Burger(1, Bread.BRIOCHE);
        burger.appendAddOn(AddOns.LETTUCE);
        assertEquals(7.29, burger.price(), 0.001);
    }

    /** Verifies two double-patty burgers with cheese and tomatoes. */
    @Test
    public void testDoublePattyWithCheeseAndTomatoes() {
        Burger burger = new Burger(2, Bread.PRETZEL);
        burger.setDoublePatty(true);
        burger.appendAddOn(AddOns.CHEESE);
        burger.appendAddOn(AddOns.TOMATOES);
        assertEquals(21.58, burger.price(), 0.001);
    }
}
