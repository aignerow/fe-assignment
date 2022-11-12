Feature: Cookies setup

  Scenario: After entering page for the first time, user has cookies default setup displayed
    When user go to the FedEx main page without changing language
    Then default cookies setup is as expected

  Scenario Outline: User applies custom setup of cookies
    Given user go to the FedEx main page without changing language
    When select cookies options <cookiesOptions>
    Then user is on the <pageName>
    Examples:
      | cookiesOptions                            | pageName |
      | Other Analytical Cookies                  | HomePage |
      | Tracking Cookies                          | HomePage |
      | Other Analytical Cookies,Tracking Cookies | HomePage |

  Scenario: User accept all cookies
    Given user go to the FedEx main page without changing language
    And default cookies setup is as expected
    When user accept all cookies
    Then user is on the HomePage


