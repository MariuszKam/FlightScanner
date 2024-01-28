package com.solvd.unintegrated.pathfinder.impl;

import com.solvd.unintegrated.pathfinder.PathfindingService;
import com.solvd.unintegrated.pathfinder.dummy.DummyAirport;
import com.solvd.unintegrated.pathfinder.dummy.DummyFlight;
import com.solvd.unintegrated.pathfinder.util.EarthDistanceCalculator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.lang.Double.POSITIVE_INFINITY;

public class PathfindingServiceImpl implements PathfindingService {
    @Override
    public Optional<List<DummyFlight>> findCheapestPath( DummyAirport start, DummyAirport destination) {
        return findPathAStar(start, destination,
                PathfindingServiceImpl::lowestCostDefaultEstimator,
                PathfindingServiceImpl::calculateFlightCostByPrice,
                PathfindingServiceImpl::cheapestFlightSelector
        );
    }

    @Override
    public Optional<List<DummyFlight>> findShortestPath( DummyAirport start, DummyAirport destination) {
        return findPathAStar(start, destination,
                PathfindingServiceImpl::lowestCostDistanceEstimator,
                PathfindingServiceImpl::calculateFlightCostByDistance,
                PathfindingServiceImpl::cheapestFlightSelector
                );
    }

    private static Double calculateFlightCostByDistance(DummyFlight flight) {
        return EarthDistanceCalculator.calculateDistanceBetweenCoordinates(
                flight.getStart().getLatitude(), flight.getStart().getLongitude(),
                flight.getDestination().getLatitude(), flight.getDestination().getLongitude());
    }

    private static Double calculateFlightCostByPrice(DummyFlight flight) {
        return flight.getPrice();
    }

    private static DummyFlight cheapestFlightSelector(DummyFlight flightA, DummyFlight flightB) {
        if (flightA.getPrice() < flightB.getPrice()) {
            return flightA;
        } else {
            return flightB;
        }
    }

    private static Double lowestCostDistanceEstimator(DummyAirport start, DummyAirport destination) {
        return EarthDistanceCalculator.calculateDistanceBetweenCoordinates(
                start.getLatitude(), start.getLongitude(),
                destination.getLatitude(), destination.getLongitude());
    }

    private static Double lowestCostDefaultEstimator(DummyAirport start, DummyAirport destination) {
        return 0.0;
    }


    // TODO create custom functional interface for lowestCostToDestinationEstimator to make it more clear what is start
    //      and what is end
    private static Optional<List<DummyFlight>> findPathAStar(DummyAirport start, DummyAirport destination,
                                                             BiFunction<DummyAirport, DummyAirport, Double> lowestCostToDestinationEstimator,
                                                             Function<DummyFlight, Double> costFunction,
                                                             BinaryOperator<DummyFlight> flightSelector){
        // TODO rename
        Map<DummyAirport, DummyAirport> cameFrom = new HashMap<>();
        Map<DummyAirport, Double> estimatedPathCosts = new HashMap<>();
        Map<DummyAirport, Double> cheapestPathFromStart = new HashMap<>();
        PriorityQueue<DummyAirport> searchCandidates = new PriorityQueue<>(Comparator.comparingDouble(estimatedPathCosts::get));

        // initialize search
        estimatedPathCosts.put(start, lowestCostToDestinationEstimator.apply(start, destination));
        cheapestPathFromStart.put(start, 0.0);

        while (! searchCandidates.isEmpty()) {
            // select most promising node to update
            //VertexWrapper bestCandidate = searchCandidates.poll();
            DummyAirport bestCandidate = searchCandidates.poll();

            // check if goal was reached
            if( bestCandidate.equals(destination)) {
                return Optional.of(reconstructPath(start, destination, cameFrom, costFunction, flightSelector));
            }

            for (DummyAirport nextVertex : getNextAirports(bestCandidate)) {
                // check if this is better path to next vertex
                double potentialCheapestPathToNextVertex = estimatedPathCosts.get(nextVertex)
                        + findBestDirectFlightCost(bestCandidate, nextVertex, costFunction);
                if( potentialCheapestPathToNextVertex < cheapestPathFromStart.getOrDefault(nextVertex, POSITIVE_INFINITY)) {
                    // found better path to nextVertex
                    cameFrom.put(bestCandidate, nextVertex);
                    cheapestPathFromStart.put(nextVertex, potentialCheapestPathToNextVertex);
                    estimatedPathCosts.put(nextVertex,
                            potentialCheapestPathToNextVertex+ lowestCostToDestinationEstimator.apply(nextVertex, destination));
                    if (!searchCandidates.contains(nextVertex)) {
                        searchCandidates.add(nextVertex);
                    }
                }
            }
        }

        // search failed to find path to destination
        return Optional.empty();
    }

   private static List<DummyFlight> reconstructPath(DummyAirport start, DummyAirport destination, Map<DummyAirport,
           DummyAirport> cameFrom, Function<DummyFlight, Double> costFunction, BinaryOperator<DummyFlight> flightSelector){
        List<DummyFlight> path = new ArrayList<>();
        DummyAirport currentPoint = destination;

        while( !currentPoint.equals( start)) {
            DummyAirport prevPoint = cameFrom.get(currentPoint);

            // find best flight between two airports
            List<DummyFlight> bestFlights = findBestDirectFlights(prevPoint, currentPoint, costFunction);
            DummyFlight bestFlight = bestFlights.getFirst();
            for (int i = 1 ; i< bestFlights.size(); i++) {
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
   private static double findBestDirectFlightCost(DummyAirport start, DummyAirport destination, Function<DummyFlight, Double> costFunction ) {
       return costFunction.apply(findBestDirectFlights(start, destination, costFunction).getFirst());
   }

    /**
     * finds best (lowest cost) direct flight between two airports according to provided cost function and returns them
     * throws runtime exceptions if it fails to find them
     */
   private static List<DummyFlight> findBestDirectFlights(DummyAirport start, DummyAirport destination, Function<DummyFlight, Double> costFunction ) {
       Double lowestCost = null;
       List<DummyFlight> bestFlights = new ArrayList<>();

       for(DummyFlight flight : start.getDestinations()) {
           // select only flights that fly to destination
           if (!flight.getDestination().equals(destination)) {
               continue;
           }

           if( lowestCost == null ) {
               lowestCost = costFunction.apply(flight);
               bestFlights.add(flight);
           } else {
               Double currentFlightCost = costFunction.apply(flight);
               // TODO possible improvement to consider: make it so if two double differ by very small
               //      value they are consider equal
               if( currentFlightCost < lowestCost ) {
                   lowestCost = currentFlightCost;
                   bestFlights.clear();
                   bestFlights.add(flight);
               } else if (currentFlightCost.equals(lowestCost)) {
                   bestFlights.add(flight);
               }
           }
       }

       if(bestFlights.isEmpty()) {
           throw  new RuntimeException("Unable to find best direct fly between flights: %s and %s".formatted(start.toString(), destination.toString()));
       }
       return bestFlights;
   }

    private static List<DummyAirport> getNextAirports(DummyAirport airport) {
        return airport.getDestinations().stream()
                .map(DummyFlight::getDestination)
                .distinct()
                .toList();
    }
}
