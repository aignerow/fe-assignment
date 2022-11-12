package org.aignerow.fe.assignment.stepdefinitions;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.security.InvalidParameterException;
import org.aignerow.fe.assignment.constants.LinkName;

public class HomePageSteps extends BaseSteps {

  @Given("user go to the FedEx main page without changing language and cookies")
  public void userGoToTheFedExMainPageWithoutChangingLanguageAndCookies() {
    cookiesPage
        .goToHomePage();
    cookiesPage
        .isAt()
        .closeLanguageSelection()
        .applyCookiesSettings();
    homePage.isAt();
  }

  @When("^user click on the (.*) link")
  public void userClickOnTheTextLinkLink(String linkName) {
    switch (linkName) {
      case LinkName.SERVICE_ALERTS -> homePage.goToServiceAlertsPage();
      case LinkName.RETURN_PACKAGE -> homePage.goToReturnPackagePage();
      case LinkName.CUSTOMS_TOOLS -> homePage.goToCustomsToolsPage();
      case LinkName.CUSTOMISED_TRACKING -> homePage.goToCustomisedTrackingPage();
      case LinkName.DUTY_AND_TAXES -> homePage.goToDutyAndTaxesPage();
      case LinkName.SITE_MAP -> homePage.goToSiteMapPage();
      case LinkName.TERMS_OF_USE -> homePage.goToTermsOfUsePage();
      case LinkName.SECURITY_AND_PRIVACY -> homePage.goToSecurityAndPrivacyPage();
      default -> throw new InvalidParameterException(
          String.format("link name %s does not exist", linkName));
    }
  }

  @Then("^user is redirected to the (.*) page")
  public void userIsRedirectedToTheRedirectUrlPage(String redirectUrl) {
    assertEquals(String.format("expected url: '%s' does not match current url '%s'", redirectUrl,
        homePage.driver.getCurrentUrl()), homePage.driver.getCurrentUrl(), redirectUrl);
  }
}
