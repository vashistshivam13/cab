package com.shivamvashist.cab.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Location {

    Double x_coordinate;
    Double y_coordinate;

    //todo: edit this method
    public double distance(Location fromPoint) {
        return 1.0;
    }
}
