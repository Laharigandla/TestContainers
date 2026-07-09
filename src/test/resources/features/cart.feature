Feature: Shopping Cart

  Background:
    Given the catalog is open

  Scenario: Add first product to cart
    When the customer opens the first product
    And adds the product to the cart
    Then the cart should contain 1 item

  Scenario: Add product and navigate to cart
    When the customer opens the first product
    And adds the product to the cart
    Then the cart page should be displayed

  Scenario: Proceed from cart to checkout
    When the customer opens the first product
    And adds the product to the cart
    And proceeds to checkout from cart
    Then the checkout page should be displayed