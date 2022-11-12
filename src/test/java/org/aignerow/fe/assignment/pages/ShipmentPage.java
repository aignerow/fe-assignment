package org.aignerow.fe.assignment.pages;

import org.aignerow.fe.assignment.annotations.LazyComponent;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@LazyComponent
public class ShipmentPage extends BasePage {

  @FindBy(how = How.ID, using = "fx-from-legend")
  public WebElement fromAddressLabel;

  @FindBy(how = How.ID, using = "fx-to-legend")
  public WebElement toAddressLabel;

  @Override
  public Object isAt() {
    var softAssertions = new SoftAssertions();
    softAssertions.assertThat((Boolean) wait.until((d) -> fromAddressLabel.isDisplayed())).isTrue();
    softAssertions.assertThat((Boolean) wait.until((d) -> toAddressLabel.isDisplayed())).isTrue();
    softAssertions.assertAll();

    return this;
  }
}
