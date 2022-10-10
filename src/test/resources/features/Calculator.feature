@Calculator
Feature: Calculator functionalities for sum and difference

  Rule: Calculate for integer numbers

    @IntegerCalculations
    Scenario Outline: Sum/difference of two integer numbers
      Given user has two integer numbers - <number1> and <number2>
      When user wants to <operation> the integer numbers
      Then the result is <result>

      Examples:
        | number1 | number2 | operation | result |
        | 3       | 5       | sum       | 8      |
        | 10      | 6       | subtract  | 4      |

  Rule: Calculate for float numbers

    @FloatCalculations
    Scenario Outline: Sum/difference of two float numbers
      Given user has two float numbers - <number1> and <number2>
      When user wants to <operation> the float numbers
      Then the result is <result>

      Examples:
        | number1 | number2 | operation | result |
        | 1.2     | 3.0     | sum       | 4.2    |
        | 7.5     | 2.5     | subtract  | 5.0    |

  Rule: Compare two columns with numbers

    @ColumnCalculations
    Scenario Outline: Sum/Difference of numbers by column
      Given user has the following numbers
        | 2  | 7  |
        | 3  | 4  |
        | 1  | 0  |
        | 10 | 16 |
      When user wants to <operation> all numbers in <column1> column
      And user wishes to <operation> all numbers in <column2> column
      Then the result in the <column1> column is <resultFirstColumn>
      And the result in the <column2> column is <resultSecondColumn>
      And the calculation of the second column is <comparisonOperator> than the calculation of the first column

      Examples:
        | operation | column1 | column2 | resultFirstColumn | resultSecondColumn | comparisonOperator |
        | sum       | 0       | 1       | 16                | 27                 | bigger             |
        | subtract  | 0       | 1       | -12               | -13                | less               |


