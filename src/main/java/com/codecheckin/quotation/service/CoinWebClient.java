package com.codecheckin.quotation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CoinWebClient {

    private final WebClient webClient;

    public CoinWebClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://api.bithumb.com/public/ticker").build();
    }

    public Mono<JsonNode> getCoinTickers() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/ALL_KRW").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .mapNotNull(this::stringToJsonNode);
    }

    private JsonNode stringToJsonNode (String str) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
