package com.defercontextual.demo.business;

import reactor.core.publisher.Mono;

public interface DemoService {
  Mono<Object> retrieve();
}
