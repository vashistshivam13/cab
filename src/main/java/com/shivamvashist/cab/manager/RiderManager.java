package com.shivamvashist.cab.manager;

import com.shivamvashist.cab.exceptions.RiderAlreadyExistException;
import com.shivamvashist.cab.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RiderManager {

    HashMap<String, Rider> riders = new HashMap<>();

    public void registerRider(@NonNull Rider rider){
        if(riders.containsKey(rider.getId())){
            throw new RiderAlreadyExistException();
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
