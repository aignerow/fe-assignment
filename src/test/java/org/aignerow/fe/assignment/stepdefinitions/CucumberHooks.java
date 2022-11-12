package org.aignerow.fe.assignment.stepdefinitions;

import org.aignerow.fe.assignment.annotations.LazyAutowired;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.aignerow.fe.assignment.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

public class CucumberHooks {

  @LazyAutowired
  private ScreenshotUtil screenshotUtil;

  @LazyAutowired
  private ApplicationContext applicationContext;

  @AfterStep
  public void afterStep(Scenario scenario) {
    if (scenario.isFailed()) {
      scenario.attach(this.screenshotUtil.getScreenshot(), "image/png", scenario.getName());
    }
  }

  @After
  public void afterScenario() {
    this.applicationContext.getBean(WebDriver.class).quit();
  }
}
