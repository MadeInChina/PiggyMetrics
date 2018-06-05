package com.piggymetrics.statistics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import lombok.Data;

@JsonIgnoreProperties(
  ignoreUnknown = true,
  value = {"date"}
)
@Data
public class ExchangeRatesContainer {

  private LocalDate date = LocalDate.now();

  private Currency base;

  private Map<String, BigDecimal> rates;

  @Override
  public String toString() {
    return "RateList{" + "date=" + date + ", base=" + base + ", rates=" + rates + '}';
  }
}
