package org.selenium.sdet.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.sdet.support.Config;
import org.selenium.sdet.support.DriverFactory;

import static org.junit.jupiter.api.Assertions.*;

public class SmokeTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.createChromeDriver();
    }

    @AfterEach
    void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @Test
    @DisplayName("Product catalog loads in a real Chrome session")
    void catalogLoads(){
        driver.get(Config.catalogUrl());

        By catalogHeading = By.id("catalog-title");

        assertAll(
                () -> assertTrue(driver.getTitle().contains("SDET Retail")),
                () -> assertTrue(driver.findElement(catalogHeading).isDisplayed()),
                () -> assertEquals("Product Catalog",driver.findElement(catalogHeading).getText())
        );
    }
}
