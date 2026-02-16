package com.mason.selenium.cucumber.steps;

import com.mason.selenium.pages.LoginPage;
import com.mason.selenium.pages.SecureAreaPage;
import com.mason.selenium.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FormAuthenticationSteps {

    private LoginPage loginPage;
    private SecureAreaPage secureAreaPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        WebDriver driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        secureAreaPage = new SecureAreaPage(driver);

        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the secure area")
    public void i_should_see_the_secure_area() {
        Assert.assertEquals(secureAreaPage.getHeaderText(), "Secure Area");

        String flash = loginPage.getFlashText();
        Assert.assertTrue(flash.contains("You logged into a secure area!"),
                "Expected success flash message, but got: " + flash);
    }

    @Then("I should see a login error message")
    public void i_should_see_a_login_error_message() {
        String flash = loginPage.getFlashText();
        Assert.assertTrue(flash.contains("Your username is invalid!"),
                "Expected invalid login flash message, but got: " + flash);
    }
}
