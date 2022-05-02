package com.tqs.hw.api.services;

import java.util.Date;

import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.data.providers.Covid19CoronavirusStatisticsDataProvider;
import com.tqs.hw.api.data.providers.Covid19DataProvider;
import com.tqs.hw.api.data.providers.Covid19StatisticsDataProvider;
import com.tqs.hw.api.data.providers.DataProvider;
import com.tqs.hw.api.data.providers.VacCovidDataProvider;
import com.tqs.hw.api.http.CachedHttpClient;
import com.tqs.hw.api.http.HttpClient;

import org.springframework.stereotype.Service;

@Service
public class CovidService implements DataProvider {

  private HttpClient httpClient = new CachedHttpClient();
  
  private DataProvider[] providers = { 
    new Covid19CoronavirusStatisticsDataProvider(httpClient),
    new Covid19DataProvider(httpClient),
    new Covid19StatisticsDataProvider(httpClient),
    new VacCovidDataProvider(httpClient)
  };

  private Integer currentProvider = null;

  public CovidService () {
    if (providers.length > 0) currentProvider = 0;
  }

  public interface ProviderFunc {
    public Metric getMetric (DataProvider provider);
  }

  public Metric call(ProviderFunc func) {
    Metric result = new Metric("", null, null);

    if (currentProvider != null) {
      for (DataProvider provider : providers) {
        Metric res = func.getMetric(provider);
        if (res != null) result = res;
      }
    }

    return result;
  }
  
  @Override
  public Metric getCovidCases() {
    return call((DataProvider provider) -> provider.getCovidCases());
  }
  @Override
  public Metric getCovidCases(Date date) {
    return call((DataProvider provider) -> provider.getCovidCases(date));
  }
  @Override
  public Metric getCovidCases(Date startDate, Date endDate) {
    return call((DataProvider provider) -> provider.getCovidCases(startDate, endDate));
  }
  @Override
  public Metric getCovidCases(String country) {
    return call((DataProvider provider) -> provider.getCovidCases(country));
  }
  @Override
  public Metric getCovidCases(String country, Date date) {
    return call((DataProvider provider) -> provider.getCovidCases(country, date));
  }
  @Override
  public Metric getCovidCases(String country, Date startDate, Date endDate) {
    return call((DataProvider provider) -> provider.getCovidCases(country, startDate, endDate));
  }

  public DataProvider[] getProviders() {
    return this.providers;
  }

  public void setProviders(DataProvider[] dataProviders) {
    this.providers = dataProviders;
  }

  public Integer getCurrentProvider() {
    return this.currentProvider;
  }

  public void setCurrentProvider(Integer currentProvider) {
    if (currentProvider != null && (currentProvider < 0 || currentProvider >= providers.length)) currentProvider = null;

    this.currentProvider = currentProvider;
  }
  
}
