package com.tqs.hw.api.data;

public class Metric {
  
  private final String country;
  private final Period period;
  private final Integer value;

  public Metric(String country, Period period, Integer value) {
    this.country = country;
    this.period = period;
    this.value = value;
  }

  public String getCountry() {
    return this.country;
  }

  public Period getPeriod() {
    return this.period;
  }

  public Integer getValue() {
    return this.value;
  }

}
