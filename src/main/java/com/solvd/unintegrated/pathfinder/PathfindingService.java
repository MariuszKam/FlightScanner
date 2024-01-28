package com.solvd.unintegrated.pathfinder;

import com.solvd.unintegrated.pathfinder.dummy.DummyAirport;
import com.solvd.unintegrated.pathfinder.dummy.DummyFlight;

import java.util.List;
import java.util.Optional;

public interface PathfindingService {
    Optional<List<DummyFlight>> findCheapestPath(DummyAirport start,DummyAirport destination);
    Optional<List<DummyFlight>> findShortestPath(DummyAirport start,DummyAirport destination);
}
