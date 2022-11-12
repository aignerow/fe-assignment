package org.aignerow.fe.assignment.pages;

import org.aignerow.fe.assignment.annotations.LazyComponent;
import org.aignerow.fe.assignment.constants.Constants;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@LazyComponent
public class RateAndTransitTimesPage extends BasePage {

  @FindBy(how = How.ID, using = "magr-heading")
  public WebElement heading;

  @FindBy(how = How.ID, using = "fromGoogleAddress")
  public WebElement fromGoogleAddress;

  @FindBy(how = How.ID, using = "toGoogleAddress")
  public WebElement toGoogleAddress;

  @FindBy(how = How.CSS, using = "#e2eGoogleAddressSuggestionList > li:nth-child(1) > fdx-icon > svg")
  public WebElement firstOptionFromGoogleAddressSuggestionList;

  @FindBy(how = How.XPATH, using = "//*[@id='package-details__liability-coverage-yes']/following-sibling::*")
  public WebElement additionalCoverYesRadio;

  @FindBy(how = How.XPATH, using = "//*[@id='package-details__liability-coverage-no']/following-sibling::*")
  public WebElement additionalCoverNoRadio;

  @FindBy(how = How.ID, using = "package-details__package-type")
  public WebElement packagingDropdown;

  @FindBy(how = How.ID, using = "packageShipDate")
  public WebElement shipDateDropdown;

  @FindBy(how = How.XPATH, using = "//*[text()=' Add another package ']")
  public WebElement addAnotherPackageButton;

  @FindBy(how = How.ID, using = "e2ePackageDetailsSubmitButtonRates")
  public WebElement showRatesButton;

  @FindBy(how = How.XPATH, using = "//*[text()='Ship now']")
  public WebElement shipNowButton;

  @FindBy(how = How.ID, using = "rateSummary")
  public WebElement rateSummaryLabel;


  public RateAndTransitTimesPage fillInFromField(String place) {
    writeText(fromGoogleAddress, place);
    click(firstOptionFromGoogleAddressSuggestionList);
    wait.until((d) -> fromGoogleAddress.getAttribute("aria-invalid").equals("false"));
    return this;
  }

  public RateAndTransitTimesPage fillInToField(String place) {
    writeText(toGoogleAddress, place);
    click(firstOptionFromGoogleAddressSuggestionList);
    wait.until((d) -> toGoogleAddress.getAttribute("aria-invalid").equals("false"));
    return this;
  }

  public RateAndTransitTimesPage fillInAdditionalCoverRadio(boolean additionalCover) {
    if (additionalCover) {
      click(additionalCoverYesRadio);
    } else {
      click(additionalCoverNoRadio);
    }
    return this;
  }

  public RateAndTransitTimesPage fillInPackagingDropdown(String packaging) {
    packaging = String.format(" %s ", packaging.trim());

    var softAssertions = new SoftAssertions();
    softAssertions
        .assertThat(Constants.LIST_OF_PACKAGING_OPTIONS)
        .as(String.format("'%s' is not equal to any valid option: %s", packaging,
            Constants.LIST_OF_PACKAGING_OPTIONS))
        .contains(packaging);
    softAssertions.assertAll();

    selectValueFromDropdownByVisibleText(packagingDropdown, packaging);

    return this;
  }

  public RateAndTransitTimesPage fillInShipDateDropdown(String shipDate) {
    var softAssertions = new SoftAssertions();
    softAssertions
        .assertThat(Constants.LIST_OF_SHIPPING_DATE_OPTIONS)
        .as(String.format("'%s' is not equal to any valid option: %s", shipDate,
            Constants.LIST_OF_SHIPPING_DATE_OPTIONS))
        .contains(shipDate);
    softAssertions.assertAll();

    if (shipDate.equalsIgnoreCase(Constants.EARLIEST)) {
      selectValueFromDropdownByIndex(shipDateDropdown, 0);
    }
    if (shipDate.equalsIgnoreCase(Constants.LATEST)) {
      selectValueFromDropdownByIndex(shipDateDropdown, 1);
    }

    return this;
  }


  public RateAndTransitTimesPage fillInNumberOfPackages(String numberOfPackages, int i) {
    var numberOfPackagesDropdown = By.id(String.format("package-details__quantity-%s", i));
    selectValueFromDropdownByVisibleText(numberOfPackagesDropdown, numberOfPackages);

    return this;
  }

  public RateAndTransitTimesPage fillInWeightPerPackage(String weightPerPackage, int i) {
    var weightPerPackageField = By.id(String.format("package-details__weight-%s", i));
    writeText(weightPerPackageField, weightPerPackage);

    return this;
  }

  public RateAndTransitTimesPage fillInDeclaredValuePerPackage(String packageValue, int i) {
    if (packageValue != null && !packageValue.isEmpty()) {
      var declaredPackageValueField = By.id(String.format("package-details__value-%s", i));
      writeText(declaredPackageValueField, packageValue);
    }

    return this;
  }

  public RateAndTransitTimesPage clickAddAnotherPackageButton() {
    click(addAnotherPackageButton);

    return this;
  }

  public RateAndTransitTimesPage clickShowRatesButton() {
    click(showRatesButton);

    return this;
  }

  public RateAndTransitTimesPage validateThatRatesAndDeliveryDatesWereCalculated() {
    waitElement(rateSummaryLabel);

    var softAssertions = new SoftAssertions();
    softAssertions
        .assertThat(readText(rateSummaryLabel))
        .as("rate summary does not contain expected text")
        .contains("Here are the rates and delivery dates for shipments sent");
    softAssertions.assertAll();

    return this;
  }

  public RateAndTransitTimesPage clickShipNowButton() {
    click(shipNowButton);
    return this;
  }

  public RateAndTransitTimesPage fillInPackageDimensionsInCm(String packageDimensions, int i) {
    var dimensions = packageDimensions.split("x");
    var lengthField = findDimensionElement(i, Constants.LENGTH);
    var widthField = findDimensionElement(i, Constants.WIDTH);
    var heightField = findDimensionElement(i, Constants.HEIGHT);

    writeText(lengthField, dimensions[0]);
    writeText(widthField, dimensions[1]);
    writeText(heightField, dimensions[2]);
    return this;
  }

  private By findDimensionElement(int i, String dimension) {
    return By.xpath(String.format(
        "//*[@id=\"package-details__dimensions-%s\"]/input[contains(@aria-label,'%s')]", i,
        dimension));
  }

  @Override

  public Object isAt() {

    var softAssertions = new SoftAssertions();
    softAssertions.assertThat((Boolean) wait.until((d) -> heading.isDisplayed())).isTrue();
    softAssertions.assertThat(readText(heading))
        .isEqualTo(Constants.CALCULATE_FEDEX_SHIPPING_RATES);
    softAssertions.assertAll();

    return this;
  }
}
