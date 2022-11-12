Feature: Rate and transit times

  Scenario: Rate and transit times calculation for one package without additional cover
    Given user go to the FedEx main page without changing language and cookies
    When user calculate rate and transit time from Gdansk to Amsterdam:
      | additionalCover | packaging      | shipDate |
      | false           | Your Packaging | earliest |
    And user declare packages:
      | noOfPackages | weightPerPackage | dimensionsInCm | declaredValue |
      | 1            | 1                | 12x12x12       |               |
    Then rates are calculated
    And user use ship now option and is redirected to ShipmentPage

  Scenario: Rate and transit times calculation for three packages with additional cover
    Given user go to the FedEx main page without changing language and cookies
    When user calculate rate and transit time from Gdansk to Amsterdam:
      | additionalCover | packaging      | shipDate |
      | true            | Your Packaging | latest   |
    And user declare packages:
      | noOfPackages | weightPerPackage | dimensionsInCm | declaredValue |
      | 2            | 4                | 12x13x14       | 34            |
      | 3            | 5                | 33x34x55       | 55            |
      | 35           | 10               | 10x11x99       | 99            |
    Then rates are calculated
    And user use ship now option and is redirected to LoginPage





