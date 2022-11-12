package org.aignerow.fe.assignment.pages;

import static org.junit.Assert.assertTrue;

import org.aignerow.fe.assignment.annotations.LazyComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@LazyComponent
public class HomePage extends BasePage {

  @FindBy(how = How.ID, using = "btnSingleTrack")
  public WebElement trackButton;

  @FindBy(how = How.XPATH, using = "//table[@class='fxg-table stacktable large-only tableFixerJS']//*/a[@title='Service Alerts']")
  public WebElement serviceAlertsLink;

  @FindBy(how = How.XPATH, using = "//table[@class='fxg-table stacktable large-only tableFixerJS']//*/a[@title='Return a Package']")
  public WebElement returnPackageLink;

  @FindBy(how = How.XPATH, using = "//table[@class='fxg-table stacktable large-only tableFixerJS']//*/a[@title='Customs Tools']")
  public WebElement customsToolsLink;

  @FindBy(how = How.XPATH, using = "//table[@class='fxg-table stacktable large-only tableFixerJS']//*/a[@title='Customised Tracking']")
  public WebElement customisedTrackingLink;

  @FindBy(how = How.XPATH, using = "//table[@class='fxg-table stacktable large-only tableFixerJS']//*/a[@title='Duty & Taxes']")
  public WebElement dutyAndTaxesLink;

  @FindBy(how = How.XPATH, using = "//a[@title='Site Map']")
  public WebElement siteMapLink;

  @FindBy(how = How.XPATH, using = "//a[@title='Terms of Use']")
  public WebElement termsOfUseLink;

  @FindBy(how = How.XPATH, using = "//a[@title='Security & Privacy']")
  public WebElement securityAndPrivacyLink;

  @FindBy(how = How.ID, using = "cubeOnePar")
  public WebElement rateAndTransitTimesButton;

  public HomePage goToServiceAlertsPage() {
    click(serviceAlertsLink);
    return this;
  }

  public HomePage goToReturnPackagePage() {
    click(returnPackageLink);
    return this;
  }

  public HomePage goToCustomsToolsPage() {
    click(customsToolsLink);
    return this;
  }

  public HomePage goToCustomisedTrackingPage() {
    click(customisedTrackingLink);
    return this;
  }

  public HomePage goToDutyAndTaxesPage() {
    click(dutyAndTaxesLink);
    return this;
  }

  public HomePage goToSiteMapPage() {
    click(siteMapLink);
    return this;
  }

  public HomePage goToTermsOfUsePage() {
    click(termsOfUseLink);
    return this;
  }

  public HomePage goToSecurityAndPrivacyPage() {
    click(securityAndPrivacyLink);
    return this;
  }

  public HomePage goToRateAndTransitTimesPage() {
    click(rateAndTransitTimesButton);
    return this;
  }

  @Override
  public HomePage isAt() {
    assertTrue(wait.until((d) -> trackButton.isDisplayed()));
    return this;
  }
}
