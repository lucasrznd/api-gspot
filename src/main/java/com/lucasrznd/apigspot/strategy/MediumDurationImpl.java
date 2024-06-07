package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MediumDurationImpl implements SpotPrice {

    @Override
    public BigDecimal calculatePrice(Double duration, boolean activeContract) {
        if (duration > 1.00 && duration <= 2.00) {
            return new BigDecimal("25.00");
        }

        return BigDecimal.ZERO;
    }

}
