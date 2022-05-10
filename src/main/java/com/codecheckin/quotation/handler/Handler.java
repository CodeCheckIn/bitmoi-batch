package com.codecheckin.quotation.handler;

import com.codecheckin.quotation.dto.Ticker;
import com.codecheckin.quotation.service.CoinService;
import com.codecheckin.quotation.service.CoinWebClient;
import java.util.List;
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

        Mono<List<Ticker>> coinRawData = coinWebClient.getCoinTickers()
                .map(coinService::getTargetTickerList)
                .doOnNext(coinService::compareAndUpdateCoinPrice);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(coinRawData, List.class);
    }

}
