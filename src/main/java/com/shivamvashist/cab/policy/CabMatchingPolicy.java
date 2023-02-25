package com.shivamvashist.cab.policy;

import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;

import java.util.List;
import java.util.Optional;

public interface CabMatchingPolicy {

    public Optional<Cab> matchCabToUser(Rider rider, List<Cab> selectedCabs, Location fromPoint, Location endPoint);

}
