package org.aignerow.fe.assignment.pages;

import static org.junit.Assert.assertTrue;

import org.aignerow.fe.assignment.annotations.LazyComponent;
import java.security.InvalidParameterException;
import org.aignerow.fe.assignment.constants.Constants;
import org.aignerow.fe.assignment.constants.Cookies;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@LazyComponent
public class CookiesPage extends BasePage {

  @FindBy(how = How.XPATH, using = "//a[@title='close']")
  public WebElement closeLanguageSelectionButton;

  @FindBy(how = How.XPATH, using = "//h5[text()='FedEx is using cookies']")
  public WebElement cookiesInformationLabel;

  @FindBy(how = How.XPATH, using = "//button[text()='APPLY SETTINGS']")
  public WebElement applySettingsButton;

  @FindBy(how = How.XPATH, using = "//button[text()='ACCEPT ALL COOKIES']")
  public WebElement acceptAllCookiesButton;

  @FindBy(how = How.ID, using = "level1")
  public WebElement functionalCookiesCheckbox;

  @FindBy(how = How.XPATH, using = "//label[@for='level1']")
  public WebElement functionalCookiesCheckboxLabel;

  @FindBy(how = How.ID, using = "level2")
  public WebElement otherAnalyticalCookiesCheckbox;

  @FindBy(how = How.XPATH, using = "//label[@for='level2']")
  public WebElement otherAnalyticalCookiesCheckboxLabel;

  @FindBy(how = How.ID, using = "level3")
  public WebElement trackingCookiesCheckbox;

  @FindBy(how = How.XPATH, using = "//label[@for='level3']")
  public WebElement trackingCookiesCheckboxLabel;

  public CookiesPage closeLanguageSelection() {
    click(closeLanguageSelectionButton);
    return this;
  }

  public CookiesPage applyCookiesSettings() {
    click(applySettingsButton);
    return this;
  }

  public CookiesPage acceptAllCookies() {
    click(acceptAllCookiesButton);
    return this;
  }

  public CookiesPage validateCookiesDefaultSettings() {
    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(isAttributePresent(functionalCookiesCheckbox, Constants.DISABLED))
        .as("there is no 'disabled' attribute in 'functional cookies checkbox' element")
        .isTrue();
    softAssertions.assertThat(isAttributePresent(functionalCookiesCheckbox, Constants.CHECKED))
        .as("there is no 'checked' attribute in 'functional cookies checkbox' element")
        .isTrue();
    softAssertions.assertThat(readText(functionalCookiesCheckboxLabel))
        .as("'functional cookies checkbox label' have incorrect value")
        .isEqualTo(Cookies.FUNCTIONAL);
    softAssertions.assertThat(
            isAttributePresent(otherAnalyticalCookiesCheckbox, Constants.DISABLED))
        .as("there is 'disabled' attribute in 'other analytical cookies checkbox' element")
        .isFalse();
    softAssertions.assertThat(isAttributePresent(otherAnalyticalCookiesCheckbox, Constants.CHECKED))
        .as("there is 'checked' attribute in 'other analytical cookies checkbox' element")
        .isFalse();
    softAssertions.assertThat(readText(otherAnalyticalCookiesCheckboxLabel))
        .as("'other analytical cookies checkbox label' have incorrect value")
        .isEqualTo(Cookies.OTHER_ANALYTICAL);
    softAssertions.assertThat(isAttributePresent(trackingCookiesCheckbox, Constants.DISABLED))
        .as("there is 'disabled' attribute in 'tracking cookies checkbox' element")
        .isFalse();
    softAssertions.assertThat(isAttributePresent(trackingCookiesCheckbox, Constants.CHECKED))
        .as("there is 'checked' attribute in 'tracking cookies checkbox' element")
        .isFalse();
    softAssertions.assertThat(readText(trackingCookiesCheckboxLabel))
        .as("'tracking cookies checkbox label' have incorrect value")
        .isEqualTo(Cookies.TRACKING);

    softAssertions.assertAll();

    return this;
  }

  @Override
  public CookiesPage isAt() {
    assertTrue(wait.until((d) -> cookiesInformationLabel.isDisplayed()));
    return this;
  }

  public void selectCookieCheckbox(String cookieType) {

    switch (cookieType) {
      case Cookies.OTHER_ANALYTICAL -> {
        clickAndValidateCookiesCheckbox(otherAnalyticalCookiesCheckboxLabel, "level2");
      }
      case Cookies.TRACKING -> {
        clickAndValidateCookiesCheckbox(trackingCookiesCheckboxLabel, "level3");
      }
      default -> throw new InvalidParameterException(
          String.format("cookies option '%s' does not supported", cookieType));
    }
  }

  private void clickAndValidateCookiesCheckbox(WebElement webElement, String id) {
    click(webElement);
    var script = String.format(
        "return window.getComputedStyle(document.querySelector('label[for=\\'%s\\']'),':after').getPropertyValue('content')",
        id);
    var content = (String) javascriptExecutor.executeScript(script);

    var softAssertions = new SoftAssertions();
    softAssertions
        .assertThat(content)
        .as(String.format("checkbox '%s' is not checked", webElement.toString()))
        .isNotEqualTo("none");
    softAssertions.assertAll();
  }
}