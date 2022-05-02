package com.tqs.hw.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.services.CovidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
public class CovidController {

  @Autowired
  private CovidService service;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  private Metric emptyMetric = new Metric("", null, null);

  @GetMapping("/cases")
  ResponseEntity<Metric> getWorldCases(@RequestParam(required = false) String date, @RequestParam(name = "start_date", required = false) String startDate, @RequestParam(name = "end_date", required = false) String endDate) {
    Metric result = emptyMetric;
    HttpStatus status = HttpStatus.OK;
    
    try {
      if (date != null) result = service.getCovidCases(dateFormat.parse(date));
      else if (startDate != null && endDate != null) {
        Date startDateObj = dateFormat.parse(startDate);
        Date endDateObj = dateFormat.parse(endDate);

        if (startDateObj.after(endDateObj)) status = HttpStatus.BAD_REQUEST;
        else result = service.getCovidCases(startDateObj, endDateObj);
      }
      else result = service.getCovidCases();
    } catch (ParseException e) { }
    
    if (status == HttpStatus.OK && result.getValue() == null) status = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(result, status);
  }

  @GetMapping("/cases/{country}")
  ResponseEntity<Metric> getCountryCases(@PathVariable("country") String country, @RequestParam(required = false) String date, @RequestParam(name = "start_date", required = false) String startDate, @RequestParam(name = "end_date", required = false) String endDate) {
    Metric result = emptyMetric;
    HttpStatus status = HttpStatus.OK;

    try {
      if (date != null)
        result = service.getCovidCases(country, dateFormat.parse(date));
      else if (startDate != null && endDate != null) {
        Date startDateObj = dateFormat.parse(startDate);
        Date endDateObj = dateFormat.parse(endDate);

        if (startDateObj.after(endDateObj)) status = HttpStatus.BAD_REQUEST;
        else result = service.getCovidCases(country, startDateObj, endDateObj);
      }
      else result = service.getCovidCases(country);
    } catch (ParseException e) { }

    if (status == HttpStatus.OK && result.getValue() == null) status = HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(result, status);
  }
  
}
