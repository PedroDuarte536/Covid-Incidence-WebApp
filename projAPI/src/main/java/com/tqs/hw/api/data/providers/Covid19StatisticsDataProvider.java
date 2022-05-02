package com.tqs.hw.api.data.providers;

import java.util.Date;

import com.tqs.hw.api.data.AllTimePeriod;
import com.tqs.hw.api.data.DatePeriod;
import com.tqs.hw.api.data.DateRangePeriod;
import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.http.HttpClient;

public class Covid19StatisticsDataProvider extends HTTPDataProvider {

  private String world = "World";

  public Covid19StatisticsDataProvider(HttpClient httpClient) {
    super(httpClient);
  }

  @Override
  public Metric getCovidCases() {
    // TODO Auto-generated method stub
    return new Metric(world, new AllTimePeriod(), null);
  }

  @Override
  public Metric getCovidCases(Date date) {
    // TODO Auto-generated method stub
    return new Metric(world, new DatePeriod(date), null);
  }

  @Override
  public Metric getCovidCases(Date startDate, Date endDate) {
    // TODO Auto-generated method stub
    return new Metric(world, new DateRangePeriod(startDate, endDate), null);
  }

  @Override
  public Metric getCovidCases(String country) {
    return new Metric(country, new AllTimePeriod(), null);
  }

  @Override
  public Metric getCovidCases(String country, Date date) {
    return new Metric(country, new DatePeriod(date), null);
  }

  @Override
  public Metric getCovidCases(String country, Date startDate, Date endDate) {
    return new Metric(country, new DateRangePeriod(startDate, endDate), null);
  }
  
}
