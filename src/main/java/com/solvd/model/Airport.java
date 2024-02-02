package com.solvd.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Airport {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    public String toString() {
        return '"' + name + "\" airport, " +
                "id = " + id + ", [X,Y] = [" + latitude + ',' + longitude + ']';
    }
}
