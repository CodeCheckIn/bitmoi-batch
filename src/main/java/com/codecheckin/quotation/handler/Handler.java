package com.codecheckin.quotation.handler;

import com.codecheckin.quotation.service.CoinService;
import com.codecheckin.quotation.service.CoinWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CoinWebClient coinWebClient;
    private final CoinService coinService;

    public Mono<ServerResponse> getAllCoinTicker(ServerRequest request) {
        Mono<String> result = Mono.just("ok")
                .doOnNext(x -> quotationRefreshJob());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, String.class);
    }

    public void quotationRefreshJob() {
        coinWebClient.getCoinTickers()
                .map(coinService::getTargetTickerList)
                .map(coinService::compareAndUpdateCoinPrice)
                .doOnNext(coinService::produceKafkaEvent)
                .subscribe();
    }

}
