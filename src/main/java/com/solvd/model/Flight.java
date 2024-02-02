package com.solvd.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Flight {
    private Long id;
    private String name;
    private Airport start;
    private Airport destination;
    private Airline airline;
    private Double price;

}
