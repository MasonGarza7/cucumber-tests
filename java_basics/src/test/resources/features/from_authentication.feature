@ui
Feature: Form Authentication

  Scenario: Valid login shows secure area
    Given I am on the login page
    When I login with username "tomsmith" and password "SuperSecretPassword!"
    Then I should see the secure area

  Scenario: Invalid login shows error
    Given I am on the login page
    When I login with username "bad" and password "bad"
    Then I should see a login error message
