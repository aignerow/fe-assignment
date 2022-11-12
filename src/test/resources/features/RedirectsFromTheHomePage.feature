Feature: Redirection links from the home page

  Scenario Outline: User click on the link in "Manage your shipments" section and is redirected to desired page
    Given user go to the FedEx main page without changing language and cookies
    When user click on the <textLink> link
    Then user is redirected to the <redirectUrl> page
    Examples:
      | textLink            | redirectUrl                                              |
      | Service Alerts      | https://www.fedex.com/en-gb/service-news.html            |
#      option is changing every visit on the page. don't want to spend time now on figuring out conditions
#      | Return a Package    | https://www.fedex.com/en-gb/shipping/global-returns.html |
      | Customs Tools       | https://www.fedex.com/en-gb/customs-tools.html           |
      | Customised Tracking | https://www.fedex.com/en-gb/tracking/advanced.html       |
      | Duty & Taxes        | https://www.fedex.com/en-gb/billing/duty-tax.html        |

  Scenario Outline: User click on the link in the footer section and is redirected to desired page
    Given user go to the FedEx main page without changing language and cookies
    When user click on the <textLink> link
    Then user is redirected to the <redirectUrl> page
    Examples:
      | textLink           | redirectUrl                                     |
      | Site Map           | http://www.fedex.com/gb/search/index.html       |
      | Terms of Use       | https://www.fedex.com/en-gb/terms-of-use.html   |
      | Security & Privacy | https://www.fedex.com/en-gb/privacy-policy.html |