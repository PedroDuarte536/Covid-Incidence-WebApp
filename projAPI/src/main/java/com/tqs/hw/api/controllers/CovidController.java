package com.tqs.hw.api.controllers;

import com.tqs.hw.api.data.Metric;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
public class CovidController {

  @GetMapping("/cases")
  Metric getWorldCases(@RequestParam(required = false) String date, @RequestParam(name = "start_date", required = false) String startDate, @RequestParam(name = "end_date", required = false) String endDate) {
    return null;
  }

  @GetMapping("/cases/{country}")
  Metric getCountryCases(@PathVariable("country") String country, @RequestParam(required = false) String date, @RequestParam(name = "start_date", required = false) String startDate, @RequestParam(name = "end_date", required = false) String endDate) {
    return null;
  }
  
}
