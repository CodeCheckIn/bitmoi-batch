package com.codecheckin.quotation.repository;

import com.codecheckin.quotation.model.Coin;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoinRepository extends ReactiveCrudRepository<Coin, Integer> {

}
