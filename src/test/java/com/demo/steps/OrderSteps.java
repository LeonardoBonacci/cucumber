package com.demo.steps;

import com.demo.service.OrderService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class OrderSteps {

    @Autowired
    private OrderService orderService;

    private String orderId;
    private Exception caughtException;

    @Given("the order service is available")
    public void theOrderServiceIsAvailable() {
        assertNotNull(orderService, "OrderService should be injected by Spring");
    }

    @When("I place an order for {string} with quantity {int}")
    public void iPlaceAnOrderFor(String item, int quantity) {
        orderId = orderService.placeOrder(item, quantity);
    }

    @When("I attempt to place an order for {string} with quantity {int}")
    public void iAttemptToPlaceAnOrderFor(String item, int quantity) {
        caughtException = null;
        try {
            // The service does NOT validate quantity or item name — so these calls
            // succeed when they shouldn't, which causes the "Then" assertion to fail.
            orderId = orderService.placeOrder(item, quantity);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("I should receive a valid order ID")
    public void iShouldReceiveAValidOrderId() {
        assertNotNull(orderId, "Order ID should not be null");
        assertTrue(orderId.startsWith("ORDER-"), "Order ID should start with ORDER- but was: " + orderId);
    }

    @Then("the order status should be {string}")
    public void theOrderStatusShouldBe(String expectedStatus) {
        String status = orderService.getOrderStatus(orderId);
        assertEquals(expectedStatus, status);
    }

    @Then("the order should be rejected with message {string}")
    public void theOrderShouldBeRejectedWithMessage(String expectedMessage) {
        // This assertion INTENTIONALLY FAILS because the OrderService does not
        // perform input validation — it happily processes invalid orders instead
        // of throwing an exception. This makes the @failing scenarios fail,
        // producing entries in rerun.txt for the rerun mechanism to pick up.
        assertNotNull(caughtException,
                "Expected the order to be rejected, but it was accepted with ID: " + orderId);
        assertTrue(caughtException.getMessage().contains(expectedMessage),
                "Expected message containing '" + expectedMessage + "' but got: " + caughtException.getMessage());
    }
}
