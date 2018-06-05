package com.piggymetrics.statistics.client;

import static com.github.dreamhead.moco.Moco.file;
import static com.github.dreamhead.moco.Moco.header;
import static com.github.dreamhead.moco.Moco.pathResource;
import static com.github.dreamhead.moco.MocoJsonRunner.jsonHttpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import com.piggymetrics.statistics.StatisticsApplication;
import com.piggymetrics.statistics.domain.Currency;
import com.piggymetrics.statistics.domain.ExchangeRatesContainer;
import java.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatisticsApplication.class)
public class ExchangeRatesClientTest {

  @Autowired private ExchangeRatesClient client;

  private Runner runner;

  @Before
  public void startMockServer() {
    final HttpServer server = jsonHttpServer(1080, pathResource("exchange.json"));
    server.response(header("content-type", "application/json"));
    runner = runner(server);
    runner.start();
  }

  @After
  public void stopMockServer() {
    runner.stop();
  }

  @Test
  public void shouldRetrieveExchangeRates() {

    ExchangeRatesContainer container = client.getRates(Currency.getBase());

    assertEquals(container.getDate(), LocalDate.now());
    assertEquals(container.getBase(), Currency.getBase());

    assertNotNull(container.getRates().get(Currency.EUR.name()));
    assertNotNull(container.getRates().get(Currency.RUB.name()));
  }
}
