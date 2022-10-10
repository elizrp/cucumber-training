@Text
Feature: Text expressions

  Rule: Compare strings
    Background:
      Given case-sensitivity is off

    @CaseSensitivity
    Scenario Outline: Compare words
      When the first word is <word1>
      And the second word is <word2>
      Then verify words are equal

      Examples:
        | word1    | word2    |
        | Cucumber | cucumber |
        | CUCUMBER | CUCUMBER |

    @Sentence
    Scenario Outline: Compare sentences
      When the first sentence is <sentence1>
      And the second sentence is <sentence2>
      Then verify sentences are equal

      Examples:
        | sentence1            | sentence2            |
        | Cucumber is amazing! | cucumber is amazing! |
        | CUCUMBER is amazing. | CUCUMBER is amazing. |

  Rule: Count strings/symbols in text

    @Count
    Scenario Outline: Word/char count in text
      Given the following text exists
    """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est  laborum.
    """
      When user wants to find the <countLevel> count
      Then verify the count is <expectedCount>

      Examples:
        | countLevel | expectedCount |
        | word       | 70            |
        | char       | 446           |