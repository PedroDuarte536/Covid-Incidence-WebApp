package com.tqs.hw.api.data.providers;

import java.util.Date;

import com.tqs.hw.api.data.Metric;

public interface DataProvider {

  Metric getCovidCases();

  Metric getCovidCases(Date date);

  Metric getCovidCases(Date startDate, Date endDate);

  Metric getCovidCases(String country);

  Metric getCovidCases(String country, Date date);

  Metric getCovidCases(String country, Date startDate, Date endDate);
  
}
