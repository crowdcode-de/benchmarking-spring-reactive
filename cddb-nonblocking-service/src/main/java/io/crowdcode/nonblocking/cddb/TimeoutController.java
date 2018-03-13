package io.crowdcode.nonblocking.cddb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/veryslow")
public class TimeoutController {

    @GetMapping
    public Flux<Integer> doSomething() {
        Flux<Integer> flux = Flux.just(1).map(i -> {
            doSomethingSlow();
            return i;
        });
        return flux;
    }

    private void doSomethingSlow() {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}


