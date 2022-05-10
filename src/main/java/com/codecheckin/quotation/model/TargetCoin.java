package com.codecheckin.quotation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetCoin {

    BTC("BTC", 1),
    ETH("ETH", 2),
    LTC("LTC", 3),
    ETC("ETC", 4),
    XRP("XRP", 5);

    private final String coinName;
    private final int coinId;
}
