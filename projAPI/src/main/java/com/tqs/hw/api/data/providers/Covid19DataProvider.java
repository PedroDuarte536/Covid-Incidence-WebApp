package com.tqs.hw.api.data.providers;

import java.util.Date;

import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.http.HttpClient;

public class Covid19DataProvider extends HTTPDataProvider {

  public Covid19DataProvider(HttpClient httpClient) {
    super(httpClient);
  }

  @Override
  public Metric getCovidCases() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metric getCovidCases(Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metric getCovidCases(Date startDate, Date endDate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metric getCovidCases(String country) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metric getCovidCases(String country, Date date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metric getCovidCases(String country, Date startDate, Date endDate) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
