@ui
Feature: Dropdown

  Scenario: Select dropdown options
    Given I am on the dropdown page
    When I select "Option 1" from the dropdown
    Then the selected dropdown option should be "Option 1"
    When I select "Option 2" from the dropdown
    Then the selected dropdown option should be "Option 2"
