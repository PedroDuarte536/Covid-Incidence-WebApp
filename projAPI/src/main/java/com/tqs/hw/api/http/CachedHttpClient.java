package com.tqs.hw.api.http;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class CachedHttpClient implements HttpClient {

  private final Logger logger = LoggerFactory.getLogger(CachedHttpClient.class);

  public static final int CACHE_TTL_SECONDS = 30;

  private OkHttpClient httpClient = new OkHttpClient();
  private Jedis jedis = new Jedis();

  @Override
  public String get (String url) {
    String response = this.getFromCache(url);

    if (response == null) {
      try {
        response = httpClient.newCall(new Request.Builder().url(url).header("X-RapidAPI-Key", "5790be0effmshcf45bef2d3f069fp12d204jsnb31f8d77bc90").build()).execute().body().string();

        jedis.set(url, response, (new SetParams()).ex(CACHE_TTL_SECONDS));
        logger.info("Response from '{}' was stored in cache", url);
      } catch (IOException e) {
        logger.error("Could not get a valid response from '{}'", url);
      }
    }
    return response;
  }

  public String getFromCache (String url) {
    String response = jedis.get(url);

    if (response != null) logger.info("Response from '{}' was successfully retrieved from cache", url);
    else logger.info("Response from '{}' is not stored in cache", url);
    
    return response;
  }

  public long getAmountOfCachedResponses() {
    long size = jedis.dbSize();
    logger.info("Number of elements in cache: {}", size);
    return size;
  }
  
}
