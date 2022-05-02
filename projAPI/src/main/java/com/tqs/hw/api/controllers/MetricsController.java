package com.tqs.hw.api.controllers;

import java.util.HashMap;
import java.util.Map;

import com.tqs.hw.api.http.CachedHttpClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

  private Jedis jedis = new Jedis();

  @GetMapping("/cache")
  Map<String, Object> getCacheMetrics () {
    Map<String, Object> metrics = new HashMap<>();
    metrics.put("numberCachedResponses", jedis.dbSize());
    metrics.put("cachedResponses", jedis.keys("*"));
    metrics.put("ttlTime", CachedHttpClient.CACHE_TTL_SECONDS);
    return metrics;
  }
  
}
