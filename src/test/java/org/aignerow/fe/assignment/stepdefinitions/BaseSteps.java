package org.aignerow.fe.assignment.stepdefinitions;

import org.aignerow.fe.assignment.annotations.LazyAutowired;
import org.aignerow.fe.assignment.pages.CookiesPage;
import org.aignerow.fe.assignment.pages.HomePage;
import org.aignerow.fe.assignment.pages.LoginPage;
import org.aignerow.fe.assignment.pages.RateAndTransitTimesPage;
import org.aignerow.fe.assignment.pages.ShipmentPage;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseSteps {

  @Autowired
  HomePage homePage;

  @Autowired
  CookiesPage cookiesPage;

  @Autowired
  RateAndTransitTimesPage rateAndTransitTimesPage;

  @Autowired
  LoginPage loginPage;

  @Autowired
  ShipmentPage shipmentPage;

  @LazyAutowired
  CommonSteps commonSteps;

}
