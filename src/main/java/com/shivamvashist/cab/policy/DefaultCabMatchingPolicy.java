package com.shivamvashist.cab.policy;

import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class DefaultCabMatchingPolicy implements CabMatchingPolicy {

    @Override
    public Optional<Cab> matchCabToUser(
            @NotNull Rider rider,
            @NotNull List<Cab> selectedCabs,
            @NotNull Location fromPoint,
            @NotNull Location endPoint) {
        return selectedCabs.stream()
                .filter(cab -> cab.getCurrentTrip()==null).findAny();
    }
}
