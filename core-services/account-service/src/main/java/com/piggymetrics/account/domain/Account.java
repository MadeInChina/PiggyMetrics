package com.piggymetrics.account.domain;

import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

  @Id private String name;

  private Date lastSeen;

  @Valid private List<Item> incomes;

  @Valid private List<Item> expenses;

  @Valid @NotNull private Saving saving;

  @Length(min = 0, max = 20_000)
  private String note;
}
