package io.github.bonigarcia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {

  @FindBy(name="country") private WebElement countryInput;
  @FindBy(name="date") private WebElement dateInput;
  @FindBy(name="start_date") private WebElement startDateInput;
  @FindBy(name="end_date") private WebElement endDateInput;
  @FindBy(name="submit") private WebElement submitBtn;

  @FindBy(id="resultCountry") private WebElement resultCountry;
  @FindBy(id="resultValue") private WebElement resultValue;

  public void cleanFilters () {
    countryInput.clear();
    dateInput.clear();
    startDateInput.clear();
    endDateInput.clear();
  }

  public void filterByCountry (String country) {
    cleanFilters();
    countryInput.sendKeys(country);
  }

  public void filterByDate (String date) {
    cleanFilters();
    dateInput.sendKeys(date);
  }

  public void filterByDateRange (String date_start, String date_end) {
    cleanFilters();
    startDateInput.sendKeys(date_start);
    endDateInput.sendKeys(date_end);
  }

  public void filterByCountryAndDate (String country, String date) {
    filterByDate(date);
    dateInput.sendKeys(date);
  }

  public void filterByCountryAndDateRange (String country, String date_start, String date_end) {
    filterByDateRange(date_start, date_end);
    countryInput.sendKeys(country);
  }

  public void submit () {
    submitBtn.click();
  }

  public String getResultCountry () {
    return resultCountry.getText();
  }

  public String getResultValue () {
    return resultValue.getText();
  }

}
