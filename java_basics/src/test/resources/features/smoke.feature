@smoke
Feature: Smoke

  Scenario: Open the homepage
    Given I open the homepage
    Then the page title should contain "The Internet"
