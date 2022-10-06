@DataTable
Feature: Play with data tables

  @Students
  Rule: Data table with one column that contains student names

  Example: Print a list of student names
    Given the following student names exist
      | Ivan   |
      | Mariya |
      | Vasko  |
      | Petar  |
      | Zhivko |

  @Credentials
  Rule: Data table with two columns that contain credentials data

  Example: Print credentials
    Given the following credentials exist
      | user_1@gmail.com | 12345     |
      | user_2@yahoo.com | SECRET123 |
      | user_3@abv.bg    | 0000000   |