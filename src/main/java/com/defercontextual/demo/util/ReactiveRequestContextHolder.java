package com.defercontextual.demo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactiveRequestContextHolder {
  public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

  public static Mono<ServerHttpRequest> getRequest() {
    return Mono.deferContextual(Mono::just)
      .filter(ctx -> ctx.hasKey(CONTEXT_KEY))
      .map(ctx -> ctx.get(CONTEXT_KEY))
      .transform(m -> ReactorUtil.errorIfEmpty(m,
        () -> new RuntimeException(String.format("ContextView does not contain the Lookup Key '%s'", CONTEXT_KEY))));
  }

}
