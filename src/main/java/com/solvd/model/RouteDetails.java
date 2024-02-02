package com.solvd.model;


import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RouteDetails")
public class RouteDetails {
    @XmlElement(name = "Step")
    private List<String> steps;
}
