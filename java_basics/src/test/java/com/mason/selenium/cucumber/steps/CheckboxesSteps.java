package com.mason.selenium.cucumber.steps;

import com.mason.selenium.pages.CheckboxesPage;
import com.mason.selenium.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckboxesSteps {

    private CheckboxesPage page() {
        WebDriver driver = DriverManager.getDriver();
        return new CheckboxesPage(driver);
    }

    @Given("I am on the checkboxes page")
    public void i_am_on_the_checkboxes_page() {
        page().open();
    }

    @When("I toggle checkbox 1")
    public void i_toggle_checkbox_1() {
        page().toggle(1);
    }

    @When("I toggle checkbox 2")
    public void i_toggle_checkbox_2() {
        page().toggle(2);
    }

    @Then("checkbox 1 should be selected")
    public void checkbox_1_should_be_selected() {
        Assert.assertTrue(page().isSelected(1), "Checkbox 1 was expected to be selected");
    }

    @Then("checkbox 2 should not be selected")
    public void checkbox_2_should_not_be_selected() {
        Assert.assertFalse(page().isSelected(2), "Checkbox 2 was expected to NOT be selected");
    }
}
