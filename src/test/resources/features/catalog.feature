Feature: Product Catalog

  Background:
    Given the catalog is open

  Scenario: Display products in catalog
    Then products should be displayed

  Scenario: Search for a product
    When the customer searches for "Running"
    Then matching products should be displayed

  Scenario: Sort products by price
    When the customer sorts products by "Price: Low to High"
    Then products should be sorted by ascending price

