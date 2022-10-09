@Register
Feature: Verify registration form
  The registration form functionality works as expected.
  Mandatory fields: email, username, password, repeat password
  Optional fields: phone, city

  Background:
    Given user goes to registration page
    And all fields are empty

  @Positive @PositiveRegister
  Scenario Outline: Successful registration with valid data
    When user enters "<username>" in username field
    * user enters "<email>" in email field
    * user enters "<password>" in password field
    * user enters "<repeat password>" in repeat password field
    * user enters "<phone>" in phone field
    * user enters "<city>" in city field
    * user clicks on the register button
    Then message for successful registration is displayed
    And user is redirected to login page

    Examples:
      | username | email           | password | repeat password | phone    | city  |
      | user1    | user1@test.mail | 1234     | 1234            | 08123456 | Sofia |
      | user1    | user1@test.mail | 1234     | 1234            |          |       |

  @Negative @NegativeRegister
  Scenario Outline: Unsuccessful registration
    When user enters "<username>" in username field
    * user enters "<email>" in email field
    * user enters "<password>" in password field
    * user enters "<repeat password>" in repeat password field
    * user enters "<phone>" in phone field
    * user enters "<city>" in city field
    * user clicks on the register button
    Then user stays on register page
    And registration error message is displayed

    Examples:
      | username | email           | password | repeat password | phone | city |
      |          |                 |          |                 |       |      |
      | user1    |                 | 1234     | 1234            |       |      |
      | user1    | user1@test.mail | 1234     | 12345           |       |      |