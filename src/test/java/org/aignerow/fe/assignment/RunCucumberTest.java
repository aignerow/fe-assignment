package org.aignerow.fe.assignment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest
@CucumberOptions(
    features = "src/test/resources/features",
    plugin = {"pretty", "html:target/cucumber",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json"},
    glue = {"org.aignerow"})
public class RunCucumberTest {
}
