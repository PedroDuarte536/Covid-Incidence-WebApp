package io.github.bonigarcia;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectSteps {

  private WebDriver driver;

  private IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);

  @When("I navigate to {string}")
  public void iNavigateTo(String url) {
    driver = WebDriverManager.chromedriver().create();
    driver.get(url);
  }

  @And("I select no filters")
  public void iSelectNoFilters(String from, String to) {
    indexPage.cleanFilters();
    indexPage.submit();
  }

  @And("I select country {string}")
  public void iFilterByCountry(String country) {
    indexPage.filterByCountry(country);
    indexPage.submit();
  }

  @And("I select date range starting in {string} and ending in {string}")
  public void iFilterByDateRange(String startDate, String endDate) {
    indexPage.filterByDateRange(startDate, endDate);
    indexPage.submit();
  }

  @And("Result value should be {string} and result country should be {string}")
  public void getValueANdCountryResult(String value, String country) {
    String resultCountry = indexPage.getResultCountry();
    String resultValue = indexPage.getResultValue();
    driver.quit();
    
    if (!resultCountry.equals(country)) throw new AssertionError(String.format("Expected country '%s', received '%s'", resultCountry, country));
    if (!resultValue.equals(value)) throw new AssertionError(String.format("Expected value '%s', received '%s'", resultValue, value));
  }

  @Then("Result country should be {string}")
  public void resultCountryShouldBe(String country) {
    String resultCountry = indexPage.getResultCountry();
    driver.quit();
    
    if (!resultCountry.equals(country)) throw new AssertionError(String.format("Expected country '%s', received '%s'", resultCountry, country));
  }
}
