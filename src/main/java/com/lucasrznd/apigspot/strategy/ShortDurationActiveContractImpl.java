package com.lucasrznd.apigspot.strategy;

public class ShortDurationActiveContractImpl implements SpotPrice {

    @Override
    public Double calculatePrice(Double duration, boolean activeContract) {
        return duration <= 0.35 && activeContract ? 0D : 20.00;
    }

}
