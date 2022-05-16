package com.codecheckin.quotation.router;

import com.codecheckin.quotation.handler.Handler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class QuotationRouter {

    @Bean
    public RouterFunction<ServerResponse> QuotationRouters(Handler handler) {
        return RouterFunctions.route()
                .GET("/quotations/refresh", handler::getAllCoinTicker)
                .build();
    }
}
