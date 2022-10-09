@Login
Feature: Verify login form
  The login form functionality works as expected

  Background:
    Given user goes to login page

  @Positive @PositiveLogin
  Scenario: Login with valid credentials
    Given the following user exists
      | username | password |
      | user1    | 1234     |
    When user enters "user1" as username and "1234" as password
    And user clicks on the submit button
    Then user home page is displayed

  Rule: User cannot login with incorrect or empty data in field(s)
    Background:
      Given the following user exists
        | username | password |
        | user1    | 1234     |
      But the following user does not exist
        | username | password |
        | user2    | 12345    |

    @Negative @NegativeLogin
    Scenario Outline: Login with wrong or empty username/password
      When user enters "<username>" as username and "<password>" as password
      And user clicks on the submit button
      Then user stays on login page
      * error message is displayed
      * username and password fields are cleared
      * there is a message about number of login attempts left

      Examples:
        | username | password |
        | user2    | 1234     |
        | user1    | 12345    |
        | user1    |          |
        |          | 1234     |
        |          |          |
        | user2    | 12345    |
