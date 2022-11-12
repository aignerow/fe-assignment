package org.aignerow.fe.assignment.stepdefinitions;

import io.cucumber.java.en.Then;
import java.security.InvalidParameterException;
import org.aignerow.fe.assignment.constants.PageName;

public class CommonSteps extends BaseSteps {

  @Then("^user is on the (.*)")
  public void userIsOnThePageName(String page) {
    switch (page) {
      case PageName.HOME_PAGE -> homePage.isAt();
      case PageName.COOKIES_PAGE -> cookiesPage.isAt();
      case PageName.SHIPMENT_PAGE -> shipmentPage.isAt();
      case PageName.LOGIN_PAGE -> loginPage.isAt();
      default -> throw new InvalidParameterException(
          String.format(
              "page '%s' is not supported by the 'user is on the page' step. Add new page or change parameter value",
              page));
    }
  }
}
