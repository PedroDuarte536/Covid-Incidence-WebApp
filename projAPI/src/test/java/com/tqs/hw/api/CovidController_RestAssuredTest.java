package com.tqs.hw.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tqs.hw.api.controllers.CovidController;
import com.tqs.hw.api.data.AllTimePeriod;
import com.tqs.hw.api.data.DatePeriod;
import com.tqs.hw.api.data.Metric;
import com.tqs.hw.api.services.CovidService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CovidController.class)
public class CovidController_RestAssuredTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CovidService service;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.mockMvc(mvc);
  }

  @Test
  void whenGetByCountry_ifCountryNotExists_thenStatusNotFound() {
    Mockito.when(service.getCovidCases(Mockito.anyString())).thenReturn(new Metric("", new AllTimePeriod(), null));

    RestAssuredMockMvc.get("/covid/cases/aaa").then().status(HttpStatus.NOT_FOUND);

    Mockito.verify(service, Mockito.times(1)).getCovidCases(Mockito.anyString());
  }

  @Test
  void whenGetByDate_ifNoData_thenStatusNotFound() throws ParseException {
    String dateStr = "01-01-2019";
    Date date = dateFormat.parse(dateStr);

    Mockito.when(service.getCovidCases(date)).thenReturn(new Metric("All", new DatePeriod(date), null));

    RestAssuredMockMvc.given().param("date", dateStr).when().get("/covid/cases").then().status(HttpStatus.NOT_FOUND);

    Mockito.verify(service, Mockito.times(1)).getCovidCases(Mockito.any(Date.class));
  }

  @Test
  void whenGetByDateRange_ifInvalidRange_thenStatusBadRequest() throws ParseException {
    String dateStartStr = "01-01-2022";
    String dateEndStr = "01-02-2022";

    Map<String, String> params = new HashMap<>();
    params.put("date_start", dateEndStr);
    params.put("date_end", dateStartStr);

    RestAssuredMockMvc.given().params(params).when().get("/covid/cases").then().status(HttpStatus.BAD_REQUEST).body("period", null);

    Mockito.verify(service, Mockito.never()).getCovidCases(Mockito.any(Date.class));
  }
  
}
