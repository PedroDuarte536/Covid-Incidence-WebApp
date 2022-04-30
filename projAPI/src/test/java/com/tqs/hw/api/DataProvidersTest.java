package com.tqs.hw.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tqs.hw.api.data.DatePeriod;
import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.data.providers.Covid19CoronavirusStatisticsDataProvider;
import com.tqs.hw.api.data.providers.Covid19DataProvider;
import com.tqs.hw.api.data.providers.Covid19StatisticsDataProvider;
import com.tqs.hw.api.data.providers.DataProvider;
import com.tqs.hw.api.data.providers.VacCovidDataProvider;
import com.tqs.hw.api.http.HttpClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataProvidersTest {

  private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

  @Mock HttpClient httpClient;

  @BeforeAll
  public void setup () {
    Mockito.when(httpClient.get("url")).thenReturn("arg1");
  }
  
  @ParameterizedTest
  @ValueSource(classes = { Covid19CoronavirusStatisticsDataProvider.class, Covid19DataProvider.class, Covid19StatisticsDataProvider.class, VacCovidDataProvider.class })
  public void whenSearchingFor18June2020Worldwide_thenReturnValidMetric (Class<?> sutClass) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    
    DataProvider sut = (DataProvider) sutClass.getDeclaredConstructor(HttpClient.class).newInstance(httpClient);
    Date date = format.parse("18-06-2020");

    Metric metric = sut.getCovidCases(date);

    assertNotEquals(null, metric);
    assertEquals("World", metric.getCountry());
    assertEquals(DatePeriod.class, metric.getPeriod().getClass());

    Mockito.verify(httpClient).get("url");

  }
  
  @ParameterizedTest
  @ValueSource(classes = { Covid19CoronavirusStatisticsDataProvider.class, Covid19DataProvider.class, Covid19StatisticsDataProvider.class, VacCovidDataProvider.class })
  public void whenSearchingFor18June2020InCanada_thenReturnValidMetric (Class<?> sutClass) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    
    DataProvider sut = (DataProvider) sutClass.getDeclaredConstructor(HttpClient.class).newInstance(httpClient);
    Date date = format.parse("18-06-2020");

    Metric metric = sut.getCovidCases("Canada", date);

    assertNotEquals(null, metric);
    assertNotEquals(null, metric.getValue());
    assertEquals("Canada", metric.getCountry());
    assertEquals(DatePeriod.class, metric.getPeriod().getClass());

    Mockito.verify(httpClient).get("url");
  }
  
  @ParameterizedTest
  @ValueSource(classes = { Covid19CoronavirusStatisticsDataProvider.class, Covid19DataProvider.class, Covid19StatisticsDataProvider.class, VacCovidDataProvider.class })
  public void whenSearchingForADateRange_thenReturnSumOfIndividualDates (Class<?> sutClass) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    
    DataProvider sut = (DataProvider) sutClass.getDeclaredConstructor(HttpClient.class).newInstance(httpClient);
    
    Date date17 = format.parse("17-06-2020");
    Date date18 = format.parse("18-06-2020");

    assertEquals(sut.getCovidCases(date17).getValue() + sut.getCovidCases(date18).getValue(), sut.getCovidCases(date17, date18).getValue());

    Mockito.verify(httpClient).get("url");
  }

}
