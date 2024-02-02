package com.solvd.model;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Airport")

public class Airport {
    @XmlAttribute
    private Long id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Double latitude;
    @XmlAttribute
    private Double longitude;

    public String toString() {
        return '"' + name + "\" airport, " +
                "id = " + id + ", [X,Y] = [" + latitude + ',' + longitude + ']';
    }
}
