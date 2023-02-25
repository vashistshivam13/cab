package com.shivamvashist.cab.policy;

import com.shivamvashist.cab.model.Location;

public interface FareCalculationPolicy {

    public Double calculatePrice(Location fromPoint, Location endPoint);

}
