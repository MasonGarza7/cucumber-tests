package com.mason.selenium.testng.tests;

import com.mason.selenium.pages.LoginPage;
import com.mason.selenium.pages.SecureAreaPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FormAuthenticationTest extends BaseTest {

    @Test
    public void testFormAuthenticationValidAndInvalid() {
        logger.info("STARTING Test --- Form Authentication ---");

        LoginPage loginPage = new LoginPage(driver);
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver);

        // Invalid login
        logger.info("Attempting invalid login...");
        loginPage.open().login("invalidUser", "wrongPass");

        String errorMsg = loginPage.getFlashText();
        logger.info("Invalid login message: " + errorMsg);
        assertTrue(errorMsg.contains("Your username is invalid!"),
                "Expected invalid login flash message, but got: " + errorMsg);

        // Valid login
        logger.info("Attempting valid login...");
        loginPage.open().login("tomsmith", "SuperSecretPassword!");

        String successMsg = loginPage.getFlashText();
        logger.info("Valid login message: " + successMsg);
        assertTrue(successMsg.contains("You logged into a secure area!"),
                "Expected success flash message, but got: " + successMsg);

        assertEquals(secureAreaPage.getHeaderText(), "Secure Area");

        logger.info("ENDING Test --- Completed successfully: Form Authentication ---");
    }
}
