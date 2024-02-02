package com.solvd.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
public class Flight {
    private Long id;
    private String name;
    private Airport start;
    private Airport destination;
    private Airline airline;
    private Double price;

    @Override
    public String toString() {
        return '"' + name + "\" flight, by " + airline +
                " from " + start + " to " + destination +
                " price=" + price +
                "id=" + id;
    }
}
