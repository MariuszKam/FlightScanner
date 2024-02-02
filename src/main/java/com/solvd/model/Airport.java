package com.solvd.model;

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

}
