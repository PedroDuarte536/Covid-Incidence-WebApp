package com.tqs.hw.api.services;

import java.util.Date;

import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.data.providers.DataProvider;

import org.springframework.stereotype.Service;

@Service
public class CovidService implements DataProvider {
  
  private DataProvider[] providers = { };
  private Integer currentProvider = null;
  
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
    this.currentProvider = currentProvider;
  }
  
}
