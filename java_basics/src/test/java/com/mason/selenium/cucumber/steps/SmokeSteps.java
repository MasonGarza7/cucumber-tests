package com.mason.selenium.cucumber.steps;

import com.mason.selenium.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SmokeSteps {

    @Given("I open the homepage")
    public void i_open_the_homepage() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expected) {
        WebDriver driver = DriverManager.getDriver();
        Assert.assertTrue(driver.getTitle().contains(expected), "Title did not contain expected text.");
    }
}

