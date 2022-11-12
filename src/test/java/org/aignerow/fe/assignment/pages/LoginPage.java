package org.aignerow.fe.assignment.pages;

import org.aignerow.fe.assignment.annotations.LazyComponent;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@LazyComponent
public class LoginPage extends BasePage {

  @FindBy(how = How.NAME, using = "username")
  public WebElement usernameField;

  @FindBy(how = How.NAME, using = "password")
  public WebElement passwordField;

  @Override
  public Object isAt() {
    var softAssertions = new SoftAssertions();
    softAssertions.assertThat((Boolean) wait.until((d) -> usernameField.isDisplayed())).isTrue();
    softAssertions.assertThat((Boolean) wait.until((d) -> passwordField.isDisplayed())).isTrue();
    softAssertions.assertAll();

    return this;
  }
}
