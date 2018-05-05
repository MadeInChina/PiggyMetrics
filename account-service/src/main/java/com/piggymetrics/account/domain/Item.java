package com.piggymetrics.account.domain;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Item {

  @NotNull
  @Length(min = 1, max = 20)
  private String title;

  @NotNull private BigDecimal amount;

  @NotNull private Currency currency;

  @NotNull private TimePeriod period;

  @NotNull private String icon;
}
