package com.shivamvashist.cab.controller;

import com.shivamvashist.cab.manager.CabManager;
import com.shivamvashist.cab.manager.TripManager;
import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

public class CabController {

    CabManager cabManager;

    public CabController(CabManager cabManager, TripManager tripManager) {
        this.cabManager = cabManager;
        this.tripManager = tripManager;
    }

    TripManager tripManager;

    @RequestMapping("/register/cab")
    public ResponseEntity registerCab(String id, String driverName){
        cabManager.registerCab(new Cab(id, driverName));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/update/availablity")
    public ResponseEntity updateAvailability(String id, Boolean isAvailable){
        cabManager.updateAvailability(id, isAvailable);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/endTrip")
    public ResponseEntity endTrip(String id){
        tripManager.endTrip(cabManager.getCab(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/update/location")
    public ResponseEntity updateLocation(String id, Double x_c, Double y_c){
        cabManager.updateLocation(id, new Location(x_c, y_c));
        return new ResponseEntity(HttpStatus.OK);
    }

}
