package com.tqs.hw.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tqs.hw.api.http.CachedHttpClient;

import org.junit.jupiter.api.Test;

public class CachingTest {

  private CachedHttpClient httpClient = new CachedHttpClient();
  private String testUrl = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total";

  @Test
  public void whenRequestIsMadeForTheFirstTime_thenCacheDoesNotExist () {

    assertEquals(null, httpClient.getFromCache(testUrl));

  }

  @Test
  public void whenRequestIsMade_thenCacheItsResponse () {

    String response = httpClient.get(testUrl);

    assertEquals(response, httpClient.getFromCache(testUrl));
    assertEquals(1, httpClient.getAmountOfCachedResponses());

  }
  
}
