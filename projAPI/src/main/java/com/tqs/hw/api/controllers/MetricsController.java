package com.tqs.hw.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

  @GetMapping("/cache")
  Object getCacheMetrics () {
    return null;
  }
  
}
