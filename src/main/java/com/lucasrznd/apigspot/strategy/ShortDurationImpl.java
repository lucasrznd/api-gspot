package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ShortDurationImpl implements SpotPrice {

    @Override
    public BigDecimal calculatePrice(Double duration, boolean activeContract) {
        if (duration <= 1.00) {
            return new BigDecimal("20.00");
        }

        return BigDecimal.ZERO;
    }

}
