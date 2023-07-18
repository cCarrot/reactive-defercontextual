package com.defercontextual.demo.expose;

import com.defercontextual.demo.business.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/demo/")
@RequiredArgsConstructor
public class DemoController {

  private final DemoService demoService;

  @GetMapping
  public Mono<Object> retrieveDemo(){
    log.info("Retrieving demo");
    return demoService.retrieve();
  }
}
