Feature: Create a new project

  Scenario: Create a new project with valid data
    Given User login and on Dashboard page
      | user12 |
    Then User click on New Project button
    When User fill the Name, Identifier and Task limit
      | MyProject |
      | myProject  |
      | 5            |
    And User click on Save button
    Then The project should be created
    And Remove project for retesting

  Scenario: Create a new project with with existing Identifier
    Given User login and on Dashboard page
      | user13 |
    Then User click on New Project button
    When User fill the Name, existing Identifier
      | TestProject2 |
      | test          |
    And User click on Save button
    Then An error message should appear under the Identifier field