package com.shivamvashist.cab.manager;

import com.shivamvashist.cab.exceptions.CabNotFoundException;
import com.shivamvashist.cab.exceptions.CabsNotPresentException;
import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;
import com.shivamvashist.cab.model.Trip;
import com.shivamvashist.cab.policy.CabMatchingPolicy;
import com.shivamvashist.cab.policy.DefaultCabMatchingPolicy;
import com.shivamvashist.cab.policy.DefaultFarePolicy;
import com.shivamvashist.cab.policy.FareCalculationPolicy;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class TripManager {

    Double MAX_DISTANCE_ALLOWED_FOR_TRIP = 100.0;

    HashMap<String, List<Trip>> trips = new HashMap<>();

    @Autowired
    CabManager cabManager;
    @Autowired
    RiderManager riderManager;

    CabMatchingPolicy cabMatchingPolicy;
    FareCalculationPolicy fareCalculationPolicy;

    @Autowired
    public void setCabMatchingPolicy(DefaultCabMatchingPolicy cabMatchingPolicy) {
        this.cabMatchingPolicy = cabMatchingPolicy;
    }

    @Autowired
    public void setFareCalculationPolicy(DefaultFarePolicy fareCalculationPolicy) {
        this.fareCalculationPolicy = fareCalculationPolicy;
    }


    //TODO: make this method thread safe
    public void createTrip(Rider rider, Location fromPoint, Location endPoint) {
        List<Cab> availableCabs = cabManager.getCabs(fromPoint, MAX_DISTANCE_ALLOWED_FOR_TRIP);
        Double price = fareCalculationPolicy.calculatePrice(fromPoint, endPoint);
        Optional<Cab> selectedCab = cabMatchingPolicy.matchCabToUser(rider, availableCabs, fromPoint, endPoint);
        if (!selectedCab.isPresent()) {
            throw new CabsNotPresentException();
        }
        final Cab cab = selectedCab.get();
        Trip trip = new Trip(rider, cab, fromPoint, endPoint, price);
        if (!trips.containsKey(rider.getId())) {
            trips.put(rider.getId(), new ArrayList<>());
        }
        trips.get(rider.getId()).add(trip);
        cab.setCurrentTrip(trip);

    }

    public List<Trip> getTripHistory(@NonNull final Rider rider) {
        return trips.get(rider.getId());
    }

    public void endTrip(final Cab cab) {
        if (cab.getCurrentTrip() == null) {
            throw new CabNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }


}
