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
@XmlType(name = "Flight")
public class Flight {
    @XmlAttribute
    private Long id;
    @XmlAttribute
    private String name;
    @XmlElement
    private Airport start;
    @XmlElement
    private Airport destination;
    @XmlElement
    private Airline airline;
    @XmlAttribute
    private Double price;

}
