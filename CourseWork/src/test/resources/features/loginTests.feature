Feature: Login page tests


  Scenario:Check login with valid user credentials
    Given URL to login page opened in browser
    When The user enter user name and password
      | user11 |
    And The user click on login button
    Then The Dashboard page should be displayed for valid credentials


  Scenario Outline: Check login with invalid user credentials <userName> and <password>
    Given URL to login page opened in browser
    When The user enter user name <userName> and password <password>
    And The user click on login button
    Then An error message should appear for invalid credentials

    Examples:
      | userName     | password      |
      | problem_user | admin         |
      | admin2       | incorrect_psw |

