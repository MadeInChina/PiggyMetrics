package com.piggymetrics.account.domain;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Saving {

  @NotNull private BigDecimal amount;

  @NotNull private Currency currency;

  @NotNull private BigDecimal interest;

  @NotNull private Boolean deposit;

  @NotNull private Boolean capitalization;
}
