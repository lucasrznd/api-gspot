package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LongDurationImpl implements SpotPrice {

    @Override
    public BigDecimal calculatePrice(Double duration, boolean activeContract) {
        if (duration > 2.00) {
            return new BigDecimal("35.00");
        }

        return BigDecimal.ZERO;
    }

}
