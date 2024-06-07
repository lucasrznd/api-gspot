package com.lucasrznd.apigspot.strategy;

import java.math.BigDecimal;

public interface SpotPrice {

    BigDecimal calculatePrice(Double duration, boolean activeContract);

}
