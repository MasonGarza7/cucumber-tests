package com.mason.selenium.cucumber.steps;

import com.mason.selenium.pages.DropdownPage;
import com.mason.selenium.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DropdownSteps {

    private DropdownPage page() {
        WebDriver driver = DriverManager.getDriver();
        return new DropdownPage(driver);
    }

    @Given("I am on the dropdown page")
    public void i_am_on_the_dropdown_page() {
        page().open();
    }

    @When("I select {string} from the dropdown")
    public void i_select_from_the_dropdown(String optionText) {
        page().selectByVisibleText(optionText);
    }

    @Then("the selected dropdown option should be {string}")
    public void the_selected_dropdown_option_should_be(String expectedOption) {
        Assert.assertEquals(page().getSelectedOptionText(), expectedOption);
    }
}
