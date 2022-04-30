package com.tqs.hw.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;

import com.tqs.hw.api.data.AllTimePeriod;
import com.tqs.hw.api.data.DatePeriod;
import com.tqs.hw.api.data.DateRangePeriod;
import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.data.providers.DataProvider;
import com.tqs.hw.api.services.CovidService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CovidService_UnitTest {

  @Mock(lenient = true)
  private DataProvider provider;

  @InjectMocks
  private CovidService service;

  @BeforeEach
  public void setUp() {

    Mockito.when(provider.getCovidCases()).thenReturn(new Metric("All", new AllTimePeriod(), 10));
    Mockito.when(provider.getCovidCases(any(Date.class))).thenReturn(new Metric("All", new DatePeriod(new Date()), 10));
    Mockito.when(provider.getCovidCases(any(Date.class), any(Date.class))).thenReturn(new Metric("All", new DateRangePeriod(new Date(), new Date()), 10));

    service.setProviders(new DataProvider[]{ provider });    

  }

  @Test
  void whenGetAllTimeCases_thenPeriodExtendsAllTime() {

    Metric result = service.getCovidCases();

    assertEquals(AllTimePeriod.class, result.getPeriod().getClass());

    Mockito.verify(provider, VerificationModeFactory.times(1)).getCovidCases();

  }

  @Test
  void whenGetCasesByDate_thenPeriodExtendsDate() {

    Metric result = service.getCovidCases(new Date());

    assertEquals(DatePeriod.class, result.getPeriod().getClass());

    Mockito.verify(provider, VerificationModeFactory.times(1)).getCovidCases();

  }

  @Test
  void whenGetCasesByDateRange_thenPeriodExtendsDateRange() {

    Metric result = service.getCovidCases(new Date(), new Date());

    assertEquals(DateRangePeriod.class, result.getPeriod().getClass());

    Mockito.verify(provider, VerificationModeFactory.times(1)).getCovidCases();

  }

  @Test
  void whenGetWorldwideCases_thenCountryEqualsAll() {

    Metric result = service.getCovidCases();

    assertEquals("All", result.getCountry());

    Mockito.verify(provider, VerificationModeFactory.times(1)).getCovidCases();

  }

}
