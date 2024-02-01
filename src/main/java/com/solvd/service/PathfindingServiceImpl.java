package com.solvd.service;

import com.solvd.model.Airport;
import com.solvd.model.Flight;
import com.solvd.service.serviceinterface.PathfindingService;
import com.solvd.service.utility.EarthDistanceCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.lang.Double.POSITIVE_INFINITY;

public class PathfindingServiceImpl implements PathfindingService {
    private static final Logger LOGGER = LogManager.getLogger(PathfindingServiceImpl.class.getName());

    private final FlightService flightService;

    public PathfindingServiceImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public Optional<List<Flight>> findCheapestPath(Airport start, Airport destination) {
        LOGGER.debug("starting search for cheapest path: %s -> %s".formatted(start.getName(), destination.getName()));

        // load all flights from the database and generate connections information
        List<Flight> allFlights = flightService.getAll();
        AirportConnectionsInfo airportConnectionsInfo = new AirportConnectionsInfo(allFlights);

        return findPathAStar(start, destination, airportConnectionsInfo,
                PathfindingServiceImpl::emptyEstimator,
                PathfindingServiceImpl::calculateFlightCostByPrice,
                PathfindingServiceImpl::cheapestFlightSelector
        );
    }

    @Override
    public Optional<List<Flight>> findShortestPath(Airport start, Airport destination) {
        LOGGER.debug("starting search for shortest path: %s -> %s".formatted(start.getName(), destination.getName()));

        // load all flights from the database and generate connections information
        List<Flight> allFlights = flightService.getAll();
        AirportConnectionsInfo airportConnectionsInfo = new AirportConnectionsInfo(allFlights);

        return findPathAStar(start, destination, airportConnectionsInfo,
                PathfindingServiceImpl::distanceEstimator,
                PathfindingServiceImpl::calculateFlightCostByDistance,
                PathfindingServiceImpl::cheapestFlightSelector
        );
    }

    /**
     * Calculates best path using A* algorithm
     *
     * @param start                            starting airport
     * @param destination                      airport to which to find path
     * @param airportConnectionsInfo           info about possible connections
     * @param lowestCostToDestinationEstimator function that will estimate that lowest possible cost
     *                                         to destination from some airport, it takes two arguments:
     *                                         first is airport from which to estimate cost, and second is
     *                                         final destination to which cost is estimated.
     *                                         for more details look at h(n) function at:
     *                                         https://en.wikipedia.org/wiki/A*_search_algorithm
     * @param costFunction                     function that calculates cost of each connection,
     *                                         used to find the lowest cost connection
     * @param flightSelector                   function used to select flight in case that they have the same cost
     * @return path is returned as list of flights, if found
     */
    private static Optional<List<Flight>> findPathAStar(Airport start, Airport destination, AirportConnectionsInfo airportConnectionsInfo,
                                                        BiFunction<Airport, Airport, Double> lowestCostToDestinationEstimator,
                                                        Function<Flight, Double> costFunction,
                                                        BinaryOperator<Flight> flightSelector) {
        // contains currently known best patch to each airport
        Map<Airport, Airport> cameFrom = new HashMap<>();
        // contains estimated lowest cost of path from start to destination
        // leading through each airport
        Map<Airport, Double> estimatedFullPathCosts = new HashMap<>();
        // contains cost of the cheapest know path from start to this node
        Map<Airport, Double> cheapestPathFromStart = new HashMap<>();
        PriorityQueue<Airport> searchCandidates = new PriorityQueue<>(Comparator.comparingDouble(estimatedFullPathCosts::get));
        searchCandidates.add(start);

        // initialize search
        estimatedFullPathCosts.put(start, lowestCostToDestinationEstimator.apply(start, destination));
        cheapestPathFromStart.put(start, 0.0);

        while (!searchCandidates.isEmpty()) {
            // select most promising airport to check path (and remove it from search queue)
            // most promising = one with the lowest estimated cost to destination
            Airport currentAirport = searchCandidates.poll();
            LOGGER.debug("  considering airport: %s".formatted(currentAirport.getName()));

            // check if goal was reached
            if (currentAirport.equals(destination)) {
                return Optional.of(reconstructPath(start, destination, airportConnectionsInfo, cameFrom, costFunction, flightSelector));
            }

            // update path from most promising node to nodes that it connects to
            for (Airport nextAirport : airportConnectionsInfo.getDirectDestinations(currentAirport)) {
                // check if this is better path to next airport
                double potentialCheapestPathToNextAirport = cheapestPathFromStart.get(currentAirport)
                        + findBestDirectFlightCost(currentAirport, nextAirport, airportConnectionsInfo, costFunction);
                if (potentialCheapestPathToNextAirport < cheapestPathFromStart.getOrDefault(nextAirport, POSITIVE_INFINITY)) {
                    LOGGER.debug("    found better path to %s (cost from start to this airport: %f)".formatted(
                            nextAirport.getName(), potentialCheapestPathToNextAirport));
                    // found better path to nextAirport, save it
                    cameFrom.put(nextAirport, currentAirport);
                    cheapestPathFromStart.put(nextAirport, potentialCheapestPathToNextAirport);
                    estimatedFullPathCosts.put(nextAirport,
                            potentialCheapestPathToNextAirport + lowestCostToDestinationEstimator.apply(nextAirport, destination));
                    if (!searchCandidates.contains(nextAirport)) {
                        searchCandidates.add(nextAirport);
                    }
                }
            }
        }

        // search failed to find path to destination
        return Optional.empty();
    }


    private static List<Flight> reconstructPath(Airport start, Airport destination, AirportConnectionsInfo airportConnectionsInfo,
                                                Map<Airport, Airport> cameFrom, Function<Flight, Double> costFunction,
                                                BinaryOperator<Flight> flightSelector) {
        List<Flight> path = new ArrayList<>();
        Airport currentPoint = destination;

        while (!currentPoint.equals(start)) {
            Airport prevPoint = cameFrom.get(currentPoint);

            // find best flight between two airports
            List<Flight> bestFlights = findBestDirectFlights(prevPoint, currentPoint, airportConnectionsInfo, costFunction);
            Flight bestFlight = bestFlights.getFirst();
            for (int i = 1; i < bestFlights.size(); i++) {
                // flight selector will select better flight if both have the same cost function
                bestFlight = flightSelector.apply(bestFlight, bestFlights.get(i));
            }

            path.add(bestFlight);

            currentPoint = prevPoint;
        }

        // since path is constructed in reverse it needs to be reversed
        Collections.reverse(path);
        return path;
    }

    /**
     * finds best (lowest cost) direct flight between two airports according to provided cost function and returns it's cost
     */
    private static double findBestDirectFlightCost(Airport start, Airport destination,
                                                   AirportConnectionsInfo airportConnectionsInfo,
                                                   Function<Flight, Double> costFunction) {
        return costFunction.apply(findBestDirectFlights(start, destination, airportConnectionsInfo, costFunction).getFirst());
    }

    /**
     * finds best (lowest cost) direct flight between two airports according to provided cost function and returns them
     * throws runtime exceptions if it fails to find them
     */
    private static List<Flight> findBestDirectFlights(Airport start, Airport destination,
                                                      AirportConnectionsInfo airportConnectionsInfo,
                                                      Function<Flight, Double> costFunction) {
        Double lowestCost = null;
        List<Flight> bestFlights = new ArrayList<>();

        for (Flight flight : airportConnectionsInfo.getDeparturesFrom(start)) {
            // select only flights that fly to destination
            if (!flight.getDestination().equals(destination)) {
                continue;
            }

            if (lowestCost == null) {
                lowestCost = costFunction.apply(flight);
                bestFlights.add(flight);
            } else {
                Double currentFlightCost = costFunction.apply(flight);
                // TODO possible improvement to consider: make it so if two double differ by very small
                //      value they are consider equal
                if (currentFlightCost < lowestCost) {
                    lowestCost = currentFlightCost;
                    bestFlights.clear();
                    bestFlights.add(flight);
                } else if (currentFlightCost.equals(lowestCost)) {
                    bestFlights.add(flight);
                }
            }
        }

        if (bestFlights.isEmpty()) {
            throw new RuntimeException("Unable to find best direct fly between flights: %s and %s".formatted(start.toString(), destination.toString()));
        }

        LOGGER.debug("    searching for best direct flights (%s -> %s). found following flight with cost function = %f:"
                .formatted(start.getName(), destination.getName(), lowestCost));
        bestFlights.forEach(flight -> LOGGER.debug("       %s".formatted(flight.getName())));
        return bestFlights;
    }

    private static Double calculateFlightCostByDistance(Flight flight) {
        return EarthDistanceCalculator.calculateDistanceBetweenCoordinates(
                flight.getStart().getLatitude(), flight.getStart().getLongitude(),
                flight.getDestination().getLatitude(), flight.getDestination().getLongitude());
    }

    private static Double calculateFlightCostByPrice(Flight flight) {
        return flight.getPrice();
    }

    private static Flight cheapestFlightSelector(Flight flightA, Flight flightB) {
        if (flightA.getPrice() < flightB.getPrice()) {
            return flightA;
        } else {
            return flightB;
        }
    }

    private static Double distanceEstimator(Airport start, Airport destination) {
        return EarthDistanceCalculator.calculateDistanceBetweenCoordinates(
                start.getLatitude(), start.getLongitude(),
                destination.getLatitude(), destination.getLongitude());
    }


    private static Double emptyEstimator(Airport start, Airport destination) {
        return 0.0;
    }

    /**
     * utility class that provides information about connections
     * between airports
     */
    private static class AirportConnectionsInfo {
        Map<Airport, List<Flight>> connectionsMap;

        /**
         * @param allFlights list of all flights
         */
        public AirportConnectionsInfo(List<Flight> allFlights) {
            this.connectionsMap = generateConnectionMap(allFlights);
        }

        List<Flight> getDeparturesFrom(Airport airport) {
            return Collections.unmodifiableList(this.connectionsMap.get(airport));
        }

        public List<Airport> getDirectDestinations(Airport fromAirport) {
            return this.connectionsMap.get(fromAirport).stream()
                    .map(Flight::getDestination)
                    .distinct()
                    .toList();
        }

        private final Map<Airport, List<Flight>> generateConnectionMap(List<Flight> flights) {
            Map<Airport, List<Flight>> flightsFrom = new HashMap<>();
            for (Flight flight : flights) {
                Airport startAirport = flight.getStart();
                if (startAirport == null) {
                    throw new RuntimeException("Start airport is null for flight: " + flight.getName());
                }
                if (!flightsFrom.containsKey(startAirport)) {
                    flightsFrom.put(flight.getStart(), new ArrayList<>());
                }
                flightsFrom.get(startAirport).add(flight);
            }
            return flightsFrom;
        }
    }

}
