package io.crowdcode.benchmark.blocking.cddb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/veryslow")
public class TimeoutController {

    @GetMapping
    public Flux<Integer> doSomething(@RequestParam(name = "wait", required = false) final Optional<Integer> waitInSeconds) {
        Flux<Integer> flux = Flux.just(1).map(i -> {
            doSomethingSlow(waitInSeconds);
            return i;
        });
        return flux;
    }

    private void doSomethingSlow(Optional<Integer> waitInSeconds) {
        try {
            System.out.println(">" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(waitInSeconds.orElse(20));
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}


