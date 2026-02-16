@ui
Feature: Checkboxes

  Scenario: Toggle checkboxes
    Given I am on the checkboxes page
    When I toggle checkbox 1
    And I toggle checkbox 2
    Then checkbox 1 should be selected
    And checkbox 2 should not be selected
