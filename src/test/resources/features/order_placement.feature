Feature: Order Placement
  As a customer
  I want to place orders for products
  So that I can receive the items I need

  Scenario: Successfully place an order for a single item
    Given the order service is available
    When I place an order for "Laptop" with quantity 1
    Then I should receive a valid order ID
    And the order status should be "CONFIRMED"

  Scenario: Place an order for multiple items
    Given the order service is available
    When I place an order for "Keyboard" with quantity 5
    Then I should receive a valid order ID
    And the order status should be "CONFIRMED"
