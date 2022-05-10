package com.codecheckin.quotation.service;

import com.codecheckin.quotation.dto.Ticker;
import com.codecheckin.quotation.model.TargetCoin;
import com.codecheckin.quotation.repository.CoinRepository;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;

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

    public void compareAndUpdateCoinPrice(List<Ticker> list) {
        Flux.fromIterable(list)
                .flatMap(ticker -> coinRepository.findById(ticker.getCoinId())
                        .filter(x -> !x.getPrice().equals(ticker.getPrice()))
                        .flatMap(x -> {
                            System.out.println(ticker.getPrice());

                            x.setPrice(ticker.getPrice());
                            return Mono.just(x);
                        })
                )
                .subscribe(x-> System.out.println(x.getName() + "/" + x.getPrice()))
                ;
    }

    public String produceKafkaEvent(List<Ticker> list) {
        return Flux.fromIterable(list)
                .map(x -> {
                    System.out.println(x);
                    return x;
                }).collectList().toString();
    }
}
