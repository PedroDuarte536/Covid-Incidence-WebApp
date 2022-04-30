package com.tqs.hw.api.data;

import java.util.Date;

public class DatePeriod extends Period {

  private final Date date;

  public DatePeriod(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return this.date;
  }
  
}
