package com.solvd.unintegrated.pathfinder;

import com.solvd.unintegrated.pathfinder.PathfindingService;
import com.solvd.unintegrated.pathfinder.impl.PathfindingServiceImpl;
import com.solvd.unintegrated.pathfinder.util.EarthDistanceCalculator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(EarthDistanceCalculator.calculateDistanceBetweenCoordinates(40.7,-74,-33.8,151.2));
        PathfindingService pathfindingService = new PathfindingServiceImpl();
    }
}
