package com.tqs.hw.api.data.providers;

import com.tqs.hw.api.http.HttpClient;

public abstract class HTTPDataProvider implements DataProvider {

  private HttpClient httpClient;

  protected HTTPDataProvider(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  protected String get (String url) {
    return httpClient.get(url);
  }
  
}
