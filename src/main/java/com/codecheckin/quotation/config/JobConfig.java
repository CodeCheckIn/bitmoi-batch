package com.codecheckin.quotation.config;

import com.codecheckin.quotation.handler.Handler;
import java.time.Duration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class JobConfig {

    private final Handler handler;

    @Bean
    public void startJob() {
        Flux.interval(Duration.ofSeconds(3))
                .doOnNext(x -> handler.quotationRefreshJob())
                .subscribe();
    }
}
