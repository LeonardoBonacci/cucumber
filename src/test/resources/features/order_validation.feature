Feature: Order Validation - Intentionally Failing
  As a system administrator
  I want order validation to catch invalid inputs
  So that bad data does not enter the system

  # This scenario ALWAYS FAILS — used to test the Cucumber rerun mechanism.
  # The step deliberately triggers an assertion failure.
  @failing @now
  Scenario: Reject an order with zero quantity
    Given the order service is available
    When I attempt to place an order for "Monitor" with quantity 0
    Then the order should be rejected with message "Quantity must be greater than zero"

  # This scenario ALWAYS FAILS — the expected status deliberately mismatches.
  @failing
  Scenario: Reject an order with empty item name
    Given the order service is available
    When I attempt to place an order for "" with quantity 3
    Then the order should be rejected with message "Item name must not be empty"

  Scenario: Accept a valid high-quantity order
    Given the order service is available
    When I place an order for "USB Cable" with quantity 100
    Then I should receive a valid order ID
