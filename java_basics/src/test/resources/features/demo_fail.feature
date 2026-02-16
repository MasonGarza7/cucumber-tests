@demo_fail
Feature: Demo intentional failure

  Scenario: Intentional failure example
    Given I am on the homepage
    Then the page header should be "This text does not exist"
