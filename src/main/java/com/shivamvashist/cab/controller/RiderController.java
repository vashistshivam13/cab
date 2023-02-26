package com.shivamvashist.cab.controller;

import com.shivamvashist.cab.manager.RiderManager;
import com.shivamvashist.cab.manager.TripManager;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;
import com.shivamvashist.cab.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RiderController {

    @Autowired
    RiderManager riderManager;
    @Autowired
    TripManager tripManager;

    @RequestMapping("/register/user")
    public ResponseEntity registerUser(String id, String name){
        riderManager.registerRider(new Rider(id, name));
        return ResponseEntity.ok(riderManager.getRider(id).toString());
    }

    @RequestMapping("/book/cab")
    public ResponseEntity bookCab(String id,
                                  Double to_x, Double to_y,
                                  Double from_x, Double from_y){

        tripManager.createTrip(riderManager.getRider(id),
                new Location(from_x ,from_y),
                new Location(to_x, to_y));
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping("/user/history")
    private ResponseEntity fetchHistory(String id){
        List<Trip> tripHistory= tripManager.getTripHistory(riderManager.getRider(id));
        return ResponseEntity.ok(tripHistory);
    }

    @RequestMapping("/user/detail")
    private ResponseEntity getUser(String id){
        return ResponseEntity.ok(riderManager.getRider(id).toString());
    }

}
