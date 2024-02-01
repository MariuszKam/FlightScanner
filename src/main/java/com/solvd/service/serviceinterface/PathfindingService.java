package com.solvd.service.serviceinterface;

import com.solvd.model.Airport;
import com.solvd.model.Flight;

import java.util.List;
import java.util.Optional;

public interface PathfindingService {
    Optional<List<Flight>> findCheapestPath(Airport start, Airport destination);

    Optional<List<Flight>> findShortestPath(Airport start, Airport destination);
}
