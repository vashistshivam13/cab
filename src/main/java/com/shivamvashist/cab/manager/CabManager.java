package com.shivamvashist.cab.manager;

import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CabManager {

    HashMap<String, Cab> cabs = new HashMap<>();

    public void registerCab(@NotNull Cab cab){
        if(cabs.containsKey(cab.getId())){
            System.out.println("Already Exists!!!");
        }
        cabs.put(cab.getId(), cab);
    }

    public void updateLocation(String cabId, Location location){
        if(!cabs.containsKey(cabId)){
            System.out.println("Not Found!");
        }
        cabs.get(cabId).setCurrentLocation(location);
    }

    public Cab getCab(String cabId){
        if(!cabs.containsKey(cabId)){
            System.out.println("Not Found!");
        }
        return cabs.get(cabId);
    }

    public void updateAvailability(String cabId, Boolean isAvailable){
        if(!cabs.containsKey(cabId)){
            System.out.println("Not Found!");
        }
        cabs.get(cabId).setAvailable(isAvailable);
    }
    public List<Cab> getCabs(Location fromPoint, Double distance){
        List<Cab> resultList = new ArrayList<>();
        for(Cab cab: cabs.values()){
            if(cab.getAvailable() && cab.getCurrentLocation().distance(fromPoint)<=distance){
                resultList.add(cab);
            }
        }
        return resultList;
    }


}
