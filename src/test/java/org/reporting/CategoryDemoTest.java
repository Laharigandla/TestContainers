package org.reporting;


import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Flaky;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Framework Hardening")
@Feature("Allure Categories Demo")
@Owner("Lahari")
public class CategoryDemoTest {

    @Test
    @Flaky
    void flakyTimeoutTest() {
        throw new AssertionError("timeout while waiting for element");
    }

    @Test
    @Story("Flaky Tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fails with timeout message so it matches the Flaky category.")
    void falckyTimeoutTest(){
        throw new AssertionError("timeout while waiting for element");
    }

    @Test
    @Story("Flaky Tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fails with stale element message so it matches the Flaky category.")
    void flakyStaleElementTest(){
        throw new AssertionError("stale element reference exception");
    }

    @Test
    @Story("Flaky Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fails with connection reset message so it matches the Flaky category.")
    void flakyConnectionResetTest(){
        throw new RuntimeException("connection reset by peer");
    }

    @Test
    @Story("Broken Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Unexpected NullPointerException should appear as Broken.")
    void brokenNullPointerTest(){
        String value = null;
        value.length();
    }

    @Test
    @Story("Broken Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Unexpected ArithmeticException should appear as Broken.")
    void brokenArithmeticTest(){
        int x = 10/0;
        System.out.println(x);
    }

    @Test
    @Story("Broken Tests")
    @Severity(SeverityLevel.MINOR)
    @Description("Unexpected IndexOutOfBoundsException should appear as Broken.")
    void brokenIndexOutOfBoundsTest(){
        List<String> names = List.of("A","B");
        names.get(5);
    }

    @Test
    @Story("Product Defects")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Intentional assertion failure representing a product defect.")
    void productFailureLoginTest() {
        assertTrue(false, "Login page validation failed");
    }

    @Test
    @Story("Product Defects")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Intentional assertion failure representing a product defect.")
    void productFailureCheckoutTest() {
        assertEquals("Paid", "Pending", "Order status incorrect");
    }

    @Test
    @Story("Product Defects")
    @Severity(SeverityLevel.NORMAL)
    @Description("Intentional assertion failure representing a product defect.")
    void productFailurePriceCalculationTest() {
        assertEquals(100, 95, "Price calculation incorrect");
    }

    @Test
    @Story("Passing Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Simple passing test.")
    void passingTestOne() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @Story("Passing Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Simple passing test.")
    void passingTestTwo() {
        assertTrue(10 > 5);
    }

    @Test
    @Story("Passing Tests")
    @Severity(SeverityLevel.MINOR)
    @Description("Simple passing test.")
    void passingTestThree() {
        assertNotNull("Allure");
    }

    @Test
    @Disabled("Waiting for implementation")
    @Story("Skipped Tests")
    void skippedTestOne() {
    }

    @Test
    @Disabled("Known issue")
    @Story("Skipped Tests")
    void skippedTestTwo() {
    }

}
