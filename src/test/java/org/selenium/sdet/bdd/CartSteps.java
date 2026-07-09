package org.selenium.sdet.bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private final World world;

    public CartSteps(World world) {
        this.world = world;
    }

    @When("the customer opens the first product")
    public void theCustomerOpensTheFirstProduct() {
        world.product = world.catalog.openFirstProduct();
    }

    @When("adds the product to the cart")
    public void addsTheProductToTheCart() {
        world.cart = world.product.addToCartNew();
    }

    @Then("the cart should contain 1 item")
    public void theCartShouldContainOneItem() {
        assertEquals(1, world.cart.lineCount());
    }

    @Then("the cart page should be displayed")
    public void theCartPageShouldBeDisplayed() {
        assertTrue(
                world.driver.getCurrentUrl().contains("/cart")
        );
    }

    @When("proceeds to checkout from cart")
    public void proceedsToCheckoutFromCart() {
        world.checkout = world.cart.proceed();
    }

    @Then("the checkout page should be displayed")
    public void theCheckoutPageShouldBeDisplayed() {
        assertTrue(
                world.driver.getCurrentUrl().contains("/checkout")
        );
    }
}