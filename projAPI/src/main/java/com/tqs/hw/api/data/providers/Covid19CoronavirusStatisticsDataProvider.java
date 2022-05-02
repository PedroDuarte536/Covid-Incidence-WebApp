package com.tqs.hw.api.data.providers;

import java.util.Date;

import com.tqs.hw.api.data.AllTimePeriod;
import com.tqs.hw.api.data.DatePeriod;
import com.tqs.hw.api.data.DateRangePeriod;
import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.http.HttpClient;

import org.json.JSONObject;

public class Covid19CoronavirusStatisticsDataProvider extends HTTPDataProvider {

  private String baseUrl = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1";
  private String world = "World";

  public Covid19CoronavirusStatisticsDataProvider(HttpClient httpClient) {
    super(httpClient);
  }

  @Override
  public Metric getCovidCases() {
    JSONObject data = new JSONObject(this.get(baseUrl + "/total"));
    int total = data.getJSONObject("data").getInt("confirmed");
    
    return new Metric(world, new AllTimePeriod(), total);
  }

  @Override
  public Metric getCovidCases(Date date) {
    return new Metric(world, new DatePeriod(date), null);
  }

  @Override
  public Metric getCovidCases(Date startDate, Date endDate) {
    return new Metric(world, new DateRangePeriod(startDate, endDate), null);
  }

  @Override
  public Metric getCovidCases(String country) {
    JSONObject data = new JSONObject(this.get(baseUrl + "/total?country=" + country));
    int total = data.getJSONObject("data").getInt("confirmed");

    if (data.getJSONObject("data").getString("location").equals("Global")) return new Metric(null, new AllTimePeriod(), null);
    return new Metric(country, new AllTimePeriod(), total);
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
