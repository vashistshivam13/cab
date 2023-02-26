package com.shivamvashist.cab.model;

import com.shivamvashist.cab.enums.TripStatus;
import lombok.ToString;

@ToString
public class Trip {

    Rider rider;
    Cab cab;
    Location pickup;
    Location destination;
    Double price;
    TripStatus tripStatus;

    public Trip(Rider rider, Cab cab, Location pickup, Location destination, Double price) {
        this.rider = rider;
        this.cab = cab;
        this.pickup = pickup;
        this.destination = destination;
        this.price = price;
        this.tripStatus = TripStatus.IN_PROGRESS;
    }
    public void endTrip(){
        this.tripStatus = TripStatus.FINISHED;
    }
}
