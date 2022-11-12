package org.aignerow.fe.assignment.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aignerow.fe.assignment.annotations.TakeScreenshot;
import org.aignerow.fe.assignment.constants.Constants;

public class RateAndTransitTimesSteps extends BaseSteps {

  @When("^user calculate rate and transit time from (.*) to (.*):")
  public void userCalculateRateAndTransitTimeFromFromToTo(String from, String to,
      DataTable dataTable) {

    var shipmentDetails = dataTable.asMaps().get(0);

    homePage
        .goToRateAndTransitTimesPage();
    rateAndTransitTimesPage
        .fillInFromField(from)
        .fillInToField(to)
        .fillInAdditionalCoverRadio(
            Boolean.parseBoolean(shipmentDetails.get(Constants.ADDITIONAL_COVER)))
        .fillInPackagingDropdown(shipmentDetails.get(Constants.PACKAGING))
        .fillInShipDateDropdown(shipmentDetails.get(Constants.SHIP_DATE));
  }

  @TakeScreenshot
  @And("user declare packages:")
  public void userDeclarePackages(DataTable dataTable) {
    var packages = dataTable.asMaps();
    for (int i = 0; i < packages.size(); i++) {
      var parcel = packages.get(i);
      rateAndTransitTimesPage
          .fillInNumberOfPackages(parcel.get(Constants.NUMBER_OF_PACKAGES), i)
          .fillInWeightPerPackage(parcel.get(Constants.WEIGHT_PER_PACKAGE), i)
          .fillInPackageDimensionsInCm(parcel.get(Constants.DIMENSIONS_IN_CM), i)
          .fillInDeclaredValuePerPackage(parcel.get(Constants.DECLARED_VALUE), i);

      if (i < packages.size() - 1) {
        rateAndTransitTimesPage
            .clickAddAnotherPackageButton();
      }
    }
  }

  @Then("rates are calculated")
  public void ratesAreCalculated() {
    rateAndTransitTimesPage
        .clickShowRatesButton()
        .validateThatRatesAndDeliveryDatesWereCalculated();
  }

  @And("^user use ship now option and is redirected to (.*)")
  public void userUseShipNowOptionAndIsRedirectedToLoginPage(String pageName) {
    rateAndTransitTimesPage
        .clickShipNowButton();
    commonSteps
        .userIsOnThePageName(pageName);
  }
}
