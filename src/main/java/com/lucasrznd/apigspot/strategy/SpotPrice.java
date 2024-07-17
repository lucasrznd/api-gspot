package com.lucasrznd.apigspot.strategy;

import java.math.BigDecimal;

public interface SpotPrice {

    Double calculatePrice(Double duration, boolean activeContract);

}
