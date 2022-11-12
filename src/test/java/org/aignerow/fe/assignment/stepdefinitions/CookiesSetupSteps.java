package org.aignerow.fe.assignment.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.security.InvalidParameterException;
import org.aignerow.fe.assignment.constants.Cookies;

public class CookiesSetupSteps extends BaseSteps {

  @When("user go to the FedEx main page without changing language")
  public void userGoToTheFedExMainPageWithoutChangingLanguage() {
    cookiesPage
        .goToHomePage();
    cookiesPage
        .isAt()
        .closeLanguageSelection();
  }

  @Then("default cookies setup is as expected")
  public void defaultCookiesSetupIsAsExpected() {
    cookiesPage.validateCookiesDefaultSettings();
  }

  @When("^select cookies options (.*)")
  public void selectCookiesOptionsCookiesOptions(String cookiesOptions) {
    var options = cookiesOptions.split(",");

    for (String option : options) {
      switch (option) {
        case Cookies.OTHER_ANALYTICAL -> cookiesPage.selectCookieCheckbox(Cookies.OTHER_ANALYTICAL);
        case Cookies.TRACKING -> cookiesPage.selectCookieCheckbox(Cookies.TRACKING);
        default -> throw new InvalidParameterException(
            String.format("cookies option '%s' does not supported", option));
      }
    }
    cookiesPage.applyCookiesSettings();
  }

  @When("user accept all cookies")
  public void userAcceptAllCookies() {
    cookiesPage
        .acceptAllCookies();
  }
}
