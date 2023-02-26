package com.shivamvashist.cab.policy;

import com.shivamvashist.cab.model.Cab;
import com.shivamvashist.cab.model.Location;
import com.shivamvashist.cab.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultCabMatchingPolicy implements CabMatchingPolicy {

    @Override
    public Optional<Cab> matchCabToUser(
            @NonNull Rider rider,
            @NonNull List<Cab> selectedCabs,
            @NonNull Location fromPoint,
            @NonNull Location endPoint) {
        return selectedCabs.stream()
                .filter(cab -> cab.getCurrentTrip()==null).findAny();
    }
}
