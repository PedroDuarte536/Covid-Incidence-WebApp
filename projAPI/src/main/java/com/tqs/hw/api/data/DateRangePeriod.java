package com.tqs.hw.api.data;

import java.util.Date;

public class DateRangePeriod extends Period {

  private final Date startDate;
  private final Date endDate;

  public DateRangePeriod(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }
  
}
