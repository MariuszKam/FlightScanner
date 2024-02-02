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
@XmlType(name = "Airline")
public class Airline {
    @XmlAttribute
    private Long id;
    @XmlAttribute
    private String name;

}
