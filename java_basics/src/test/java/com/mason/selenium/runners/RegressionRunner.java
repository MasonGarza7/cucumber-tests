package com.mason.selenium.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.mason.selenium.cucumber.steps",
                "com.mason.selenium.hooks"
        },
        plugin = {
                "pretty",
                "html:results/cucumber/cucumber.html",
                "json:results/cucumber/cucumber.json"
        },
        monochrome = true,
        tags = "not @demo_fail"
)
public class RegressionRunner extends AbstractTestNGCucumberTests {
}
