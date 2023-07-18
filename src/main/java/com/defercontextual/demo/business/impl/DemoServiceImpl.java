package com.defercontextual.demo.business.impl;

import com.defercontextual.demo.business.DemoService;
import com.defercontextual.demo.client.DemoClient;
import com.defercontextual.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {
  private final DemoClient demoClient;

  @Override
  public Mono<Object> retrieve() {
    log.info("Retrieving");
    return demoClient.getApiClient()
      .map(result -> getUser());
  }

  private static Object getUser() {
    log.info("Getting user default");
    return User.builder().username("default").build();
  }
}
