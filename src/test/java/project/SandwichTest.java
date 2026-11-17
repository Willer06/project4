package project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests sandwich price calculation with different proteins and toppings.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class SandwichTest {
    /** Verifies the cost of a plain chicken sandwich. */
    @Test
    public void testChickenNoAddOns() {
        Sandwich item = new Sandwich(1, Bread.BRIOCHE, Protein.CHICKEN);
        assertEquals(8.99, item.price(), 0.001);
    }

    /** Verifies the cost of a salmon sandwich with lettuce. */
    @Test
    public void testSalmonLettuce() {
        Sandwich item = new Sandwich(1, Bread.PRETZEL, Protein.SALMON);
        item.appendAddOn(AddOns.LETTUCE);
        assertEquals(10.29, item.price(), 0.001);
    }

    /** Verifies the cost of two roast beef sandwiches with two toppings. */
    @Test
    public void testRoastBeefCheeseAvocado() {
        Sandwich item = new Sandwich(2, Bread.SOURDOUGH, Protein.ROAST_BEEF);
        item.appendAddOn(AddOns.CHEESE);
        item.appendAddOn(AddOns.AVOCADO);
        assertEquals(24.98, item.price(), 0.001);
    }
}
