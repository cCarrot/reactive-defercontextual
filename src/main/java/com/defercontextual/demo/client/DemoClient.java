package com.defercontextual.demo.client;

import com.defercontextual.demo.util.ReactiveRequestContextHolder;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoClient {
  private final WebClient webClient;

  public Mono<Boolean> getApiClient() {
    return ReactiveRequestContextHolder.getRequest()
        .flatMap(this::retrieve);
  }

  private Mono<Boolean> retrieve(ServerHttpRequest serverHttpRequest) {
    log.info("http request headers {}", serverHttpRequest.getHeaders());
    return webClient.get()
      .uri("https://run.mocky.io/v3/2c7c8792-152e-4c66-8da1-7abcf93fcf8a")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(JsonNode.class)
      .map(DemoClient::action);
  }

  private static Boolean action(JsonNode jsonNode) {
    log.info(jsonNode.toString());
    return Boolean.TRUE;
  }
}
