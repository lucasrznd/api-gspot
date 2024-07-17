package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MediumDurationImpl implements SpotPrice {

    @Override
    public Double calculatePrice(Double duration, boolean activeContract) {
        return duration > 1.00 && duration <= 2.00 ? 25.00
                : 0;
    }

}
