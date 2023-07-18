package com.defercontextual.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class WebClientConfiguration {

  @Bean
  public WebClient webClient() {
    return WebClient.builder()
      .filter(handlingResponse())
      .build();
  }

  private static ExchangeFilterFunction handlingResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
      log.info("Received status={}, headers={}",
        clientResponse.rawStatusCode(), clientResponse.headers().asHttpHeaders());
      return Mono.just(clientResponse);
    });
  }
}
