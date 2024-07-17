package com.lucasrznd.apigspot.strategy;

import org.springframework.stereotype.Component;

@Component
public class ShortDurationImpl implements SpotPrice {

    @Override
    public Double calculatePrice(Double duration, boolean activeContract) {
        if (duration <= 1.00 && !activeContract) {
            return 20.00;
        } else if (duration > 0.35 && activeContract && duration <= 1.00) {
            return 20.00;
        }
        return 0D;
    }

}
