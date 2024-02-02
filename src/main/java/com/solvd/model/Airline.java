package com.solvd.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Airline {
    private Long id;
    private String name;

}
