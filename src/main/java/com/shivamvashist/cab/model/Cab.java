package com.shivamvashist.cab.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cab {
    String id;
    String driverName;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable=true;
    }

    @Setter
    Boolean isAvailable;

    @Setter
    Location currentLocation;

    Trip currentTrip;

    public String getId() {
        return id;
    }
    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }
}
