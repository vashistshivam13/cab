package com.shivamvashist.cab.policy;

import com.shivamvashist.cab.model.Location;
import org.springframework.stereotype.Component;

@Component
public class DefaultFarePolicy implements FareCalculationPolicy {

    Double PRICE_CONSTANT = 10.0;

    @Override
    public Double calculatePrice(Location fromPoint, Location endPoint) {
        return fromPoint.distance(endPoint)*PRICE_CONSTANT;
    }
}
