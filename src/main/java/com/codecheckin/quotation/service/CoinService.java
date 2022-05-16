package com.codecheckin.quotation.service;

import com.codecheckin.quotation.dto.Ticker;
import com.codecheckin.quotation.model.Coin;
import com.codecheckin.quotation.model.TargetCoin;
import com.codecheckin.quotation.repository.CoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinService {

    private static final String TOPIC = "quotation";

    private final CoinRepository coinRepository;
    private final KafkaTemplate<String, Coin> tickerKafkaTemplate;

    public List<Ticker> getTargetTickerList(JsonNode jsonNode) {

        return Stream.of(TargetCoin.values())
                .map(e -> {
                    String coinName = e.getCoinName();
                    JsonNode coinJsonNode = jsonNode.get("data").get(e.getCoinName());
                    long timestamp = jsonNode.get("data").get("date").asLong();
                    return Ticker.getTickerFromJsonNode(coinName, coinJsonNode, timestamp);
                })
                .collect(Collectors.toList());
    }

    public Flux<Coin> compareAndUpdateCoinPrice(List<Ticker> list) {
        return Flux.fromIterable(list)
                .flatMap(ticker -> coinRepository.findById(ticker.getCoinId())
                        .filter(coin -> isNotSamePrice(coin, ticker))
                        .flatMap(coinInfo -> updateCoinPrice(ticker, coinInfo))
                );
    }

    public void produceKafkaEvent(Flux<Coin> coinFlux) {
        coinFlux.doOnNext(coin -> {
//            log.info(String.format("#### -> Producing message -> %s", coin.toString()));
            this.tickerKafkaTemplate.send(TOPIC, coin);
            System.out.println(coin.getName() + "/" + coin.getPrice());
        }).subscribe();
    }

    private boolean isNotSamePrice(Coin coin, Ticker ticker) {
        String coinPrice = coin.getPrice().stripTrailingZeros().toPlainString();
        String tickerPrice = ticker.getPrice().toString();
        return !coinPrice.equals(tickerPrice);
    }

    private Mono<Coin> updateCoinPrice(Ticker ticker, Coin coinInfo) {
        coinInfo.setPrice(ticker.getPrice());
        return coinRepository.save(coinInfo);
    }
}
