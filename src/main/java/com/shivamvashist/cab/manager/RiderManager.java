package com.shivamvashist.cab.manager;

import com.shivamvashist.cab.model.Rider;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RiderManager {

    HashMap<String, Rider> riders = new HashMap<>();

    public void registerRider(@NotNull Rider rider){
        if(riders.containsKey(rider.getId())){
            System.out.println("Already Exists");
        }
        riders.put(rider.getId(), rider);
    }

    public Rider getRider(String riderId){
        if(!riders.containsKey(riderId)){
            System.out.println("Not Found");
        }
        return riders.get(riderId);
    }

}
