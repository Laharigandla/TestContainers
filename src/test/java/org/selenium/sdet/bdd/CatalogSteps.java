package org.selenium.sdet.bdd;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogSteps {

    private final World world;

    public CatalogSteps(World world) {
        this.world = world;
    }

    @Then("products should be displayed")
    public void productsShouldBeDisplayed() {
        assertFalse(world.catalog.cards().isEmpty());
    }

    @When("the customer searches for {string}")
    public void theCustomerSearchesFor(String searchText) {
        world.catalog.searchFor(searchText);
    }

    @Then("matching products should be displayed")
    public void matchingProductsShouldBeDisplayed() {
        assertFalse(world.catalog.cards().isEmpty());
    }

    @When("the customer sorts products by {string}")
    public void theCustomerSortsProductsBy(String sortOption) {
        world.catalog.sortBy(sortOption);
    }

    @Then("products should be sorted by ascending price")
    public void productsShouldBeSortedByAscendingPrice() {

        List<Integer> prices = world.catalog.prices();

        for (int i = 1; i < prices.size(); i++) {
            assertTrue(
                    prices.get(i) >= prices.get(i - 1),
                    "Products are not sorted correctly"
            );
        }
    }

}