package com.defercontextual.demo.util;

import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactorUtil {

  public static <R> Mono<R> errorIfEmpty(Mono<R> mono, Supplier<Throwable> throwableSupplier) {
    return mono.switchIfEmpty(Mono.defer(() -> Mono.error(throwableSupplier.get())));
  }
}
