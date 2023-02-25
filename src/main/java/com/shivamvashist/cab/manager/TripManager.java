package com.shivamvashist.cab.manager;

import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;
import com.shivamvashist.cab.model.Trip;
import com.shivamvashist.cab.policy.CabMatchingPolicy;
import com.shivamvashist.cab.policy.FareCalculationPolicy;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TripManager {

    Double MAX_DISTANCE_ALLOWED_FOR_TRIP = 100.0;

    HashMap<String, List<Trip>> trips = new HashMap<>();

    CabManager cabManager;
    RiderManager riderManager;
    CabMatchingPolicy cabMatchingPolicy;
    FareCalculationPolicy fareCalculationPolicy;

    public TripManager(CabManager cabManager, RiderManager riderManager, CabMatchingPolicy cabMatchingPolicy, FareCalculationPolicy fareCalculationPolicy) {
        this.cabManager = cabManager;
        this.riderManager = riderManager;
        this.cabMatchingPolicy = cabMatchingPolicy;
        this.fareCalculationPolicy = fareCalculationPolicy;
    }

    public void createTrip(Rider rider, Location fromPoint, Location endPoint){
        List<Cab> availableCabs = cabManager.getCabs(fromPoint, MAX_DISTANCE_ALLOWED_FOR_TRIP);
        Double price = fareCalculationPolicy.calculatePrice(fromPoint, endPoint);
        Optional<Cab> selectedCab = cabMatchingPolicy.matchCabToUser(rider,availableCabs,fromPoint, endPoint);
        if(!selectedCab.isPresent()){
            System.out.println("No cab present excptn!!!");
        }
        final Cab cab = selectedCab.get();
        Trip trip = new Trip(rider, cab, fromPoint, endPoint, price);
        if(!trips.containsKey(rider.getId())){
            trips.put(rider.getId(), new ArrayList<>());
        }
        trips.get(rider.getId()).add(trip);
        cab.setCurrentTrip(trip);

    }

    public List<Trip> getTripHistory(@NotNull final Rider rider){ return trips.get(rider.getId()); }

    public void endTrip(final Cab cab){
        if(cab.getCurrentTrip() == null){
            System.out.println("cab not found!!!");
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }


}
