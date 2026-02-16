package com.mason.selenium.cucumber.steps;

import com.mason.selenium.pages.HomePage;
import com.mason.selenium.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DemoFailSteps {

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        WebDriver driver = DriverManager.getDriver();
        new HomePage(driver).open();
    }

    @Then("the page header should be {string}")
    public void the_page_header_should_be(String expectedHeader) {
        WebDriver driver = DriverManager.getDriver();
        String actualHeader = new HomePage(driver).getHeaderText();
        Assert.assertEquals(actualHeader, expectedHeader);
    }
}
