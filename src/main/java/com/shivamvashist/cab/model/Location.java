package com.shivamvashist.cab.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@NoArgsConstructor
@Setter
public class Location {

    Double x_coordinate;
    Double y_coordinate;

    public Location(Double x_coordinate, Double y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }


    public Double distance(Location location2) {
        return sqrt( pow(this.x_coordinate - location2.x_coordinate, 2) + pow(this.y_coordinate - location2.y_coordinate, 2) );
    }
}
