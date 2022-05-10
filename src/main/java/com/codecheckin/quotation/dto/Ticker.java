package com.codecheckin.quotation.dto;

import com.codecheckin.quotation.model.TargetCoin;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ticker {
    private int coinId;
    private String coinName;
    private Double price;
    private LocalDateTime date;

    public static Ticker getTickerFromJsonNode(String coinName, JsonNode jsonNode, long timestamp) {
        Double closingPrice = jsonNode.get("closing_price").asDouble();
        LocalDateTime date = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
        return new Ticker(TargetCoin.valueOf(coinName).getCoinId() , coinName, closingPrice, date);
    }
}
