package org.aignerow.fe.assignment.pages;

import javax.annotation.PostConstruct;
import org.aignerow.fe.assignment.annotations.LazyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public abstract class BasePage {

  @Value("${application.url}")
  private String baseURL;

  @Autowired
  public WebDriver driver;

  @Autowired
  protected WebDriverWait wait;

  @Autowired
  protected JavascriptExecutor javascriptExecutor;

  @PostConstruct
  private void init() {
    PageFactory.initElements(this.driver, this);
  }

  public abstract Object isAt();

  public <T> void waitElement(T elementAttr) {
    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      wait.until(ExpectedConditions.visibilityOfElementLocated((By) elementAttr));
    } else {
      wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
    }
  }

  public void goToHomePage() {
    driver.get(baseURL);
  }

  //Click Method by using JAVA Generics (You can use both By or Web element)
  public <T> void click(T elementAttr) {
    waitElement(elementAttr);
    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      driver
          .findElement((By) elementAttr)
          .click();
    } else {
      ((WebElement) elementAttr).click();
    }
  }

  public <T> void selectValueFromDropdownByVisibleText(T elementAttr, String text) {
    var select = getSelect(elementAttr);

    select.selectByVisibleText(text);
  }

  public <T> void selectValueFromDropdownByIndex(T elementAttr, int index) {
    var select = getSelect(elementAttr);

    select.selectByIndex(index);
  }

  private <T> Select getSelect(T elementAttr) {
    waitElement(elementAttr);
    Select select;

    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      select = new Select(driver
          .findElement((By) elementAttr));
    } else {
      select = new Select((WebElement) elementAttr);
    }
    return select;
  }

  //Write Text by using JAVA Generics (You can use both By or WebElement)
  public <T> void writeText(T elementAttr, String text) {
    waitElement(elementAttr);
    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
      driver
          .findElement((By) elementAttr)
          .sendKeys(text);
    } else {
      wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
      ((WebElement) elementAttr).sendKeys(text);
    }
  }

  //Read Text by using JAVA Generics (You can use both By or WebElement)
  public <T> String readText(T elementAttr) {
    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      return driver
          .findElement((By) elementAttr)
          .getText();
    } else {
      return ((WebElement) elementAttr).getText();
    }
  }

  public <T> boolean isAttributePresent(T elementAttr, String attribute) {
    WebElement webElement;

    if (elementAttr
        .getClass()
        .getName()
        .contains("By")) {
      webElement = driver
          .findElement((By) elementAttr);
    } else {
      webElement = (WebElement) elementAttr;
    }
    var value = webElement.getAttribute(attribute);
    return value != null;
  }
}
