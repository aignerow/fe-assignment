package org.aignerow.fe.assignment.configurations;

import org.aignerow.fe.assignment.annotations.LazyConfiguration;
import org.aignerow.fe.assignment.annotations.WebdriverScopeBean;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;

@LazyConfiguration
public class WebDriverConfig {

  @WebdriverScopeBean
  @ConditionalOnProperty(name = "browser", havingValue = "firefox")
  @Primary
  public WebDriver firefoxDriver() {
    return new FirefoxDriver();
  }

  @WebdriverScopeBean
  @ConditionalOnProperty(name = "browser", havingValue = "edge")
  @Primary
  public WebDriver edgeDriver() {
    return new EdgeDriver();
  }

  @WebdriverScopeBean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(name = "browser", havingValue = "chrome")
  @Primary
  public WebDriver chromeDriver() {
    return new ChromeDriver();
  }
}
