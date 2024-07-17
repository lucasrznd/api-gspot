package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

@Component
public class LongDurationImpl implements SpotPrice {

    @Override
    public Double calculatePrice(Double duration, boolean activeContract) {
        return duration > 2.00 ? 35.00 : 0;
    }

}
